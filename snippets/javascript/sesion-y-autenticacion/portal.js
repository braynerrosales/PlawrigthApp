import { readFileSync } from 'node:fs';

/**
 * Sitio de práctica `fixtures/sesion` (Portal QA) servido en `https://portal.test` interceptando
 * las peticiones del contexto; nunca sale a la red. Igual que `PortalTest` en C#.
 * Las cuentas y sus claves son ficticias, solo para estos ejemplos.
 */
export const PORTAL = 'https://portal.test';

/** @param {string} name */
const file = (name) => readFileSync(new URL(`../../fixtures/sesion/${name}`, import.meta.url), 'utf8');

/** @typedef {{ clave: string; nombre: string; rol: string; token: string }} Cuenta */
/** @type {Record<string, Cuenta>} */
const cuentas = JSON.parse(file('cuentas.json'));

/**
 * Simula el servidor: login con cookie de sesión y un panel privado que manda a /login sin ella.
 * @param {import('@playwright/test').BrowserContext} context
 */
export async function servePortal(context) {
	await context.route(`${PORTAL}/**`, async (route) => {
		const request = route.request();
		const { pathname } = new URL(request.url());

		if (pathname === '/api/login' && request.method() === 'POST') {
			const { usuario, clave } = request.postDataJSON();
			const cuenta = cuentas[usuario];
			if (!cuenta || cuenta.clave !== clave) return route.fulfill({ status: 401, json: { error: 'credenciales' } });
			return route.fulfill({
				status: 200,
				headers: { 'Set-Cookie': `sesion=${cuenta.token}; Path=/; HttpOnly; Secure; SameSite=Lax` },
				json: { nombre: cuenta.nombre },
			});
		}

		if (pathname === '/login') return route.fulfill({ contentType: 'text/html; charset=utf-8', body: file('login.html') });

		if (pathname === '/panel') {
			const token = (await request.headerValue('cookie'))?.match(/(?:^|;\s*)sesion=([^;]+)/)?.[1];
			const cuenta = Object.values(cuentas).find((c) => c.token === token);
			if (!cuenta) return route.fulfill({ status: 401, contentType: 'text/html; charset=utf-8', body: file('sin-sesion.html') });
			let html = file('panel.html').replaceAll('{{nombre}}', cuenta.nombre).replaceAll('{{rol}}', cuenta.rol);
			if (cuenta.rol !== 'admin') html = html.replace(/<!-- admin -->[\s\S]*<!-- \/admin -->/, '');
			return route.fulfill({ contentType: 'text/html; charset=utf-8', body: html });
		}

		return route.fulfill({ status: 404, contentType: 'text/plain; charset=utf-8', body: 'No encontrada' });
	});
}

/**
 * Inicia sesión por la interfaz en un contexto nuevo y guarda su estado en `path` (como un proyecto `setup`).
 * @param {import('@playwright/test').Browser} browser
 * @param {string} usuario
 * @param {string} path
 */
export async function saveSession(browser, usuario, path) {
	// Estado vacío explícito: en Playwright Test, browser.newContext() hereda las opciones de test.use().
	const context = await browser.newContext({ baseURL: PORTAL, storageState: { cookies: [], origins: [] } });
	await servePortal(context);
	const page = await context.newPage();
	await page.goto('/login');
	await page.getByLabel('Usuario').fill(usuario);
	await page.getByLabel('Contraseña').fill(cuentas[usuario].clave);
	await page.getByRole('button', { name: 'Ingresar' }).click();
	await page.waitForURL('**/panel');
	await context.storageState({ path });
	await context.close();
}
