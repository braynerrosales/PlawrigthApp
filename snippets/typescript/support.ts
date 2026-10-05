import { readFileSync } from 'node:fs';
import type { BrowserContext } from '@playwright/test';

/** Página de práctica de `snippets/fixtures`, compartida con los snippets de C# y JavaScript. */
export const fixture = (name: string) => readFileSync(new URL(`../fixtures/${name}.html`, import.meta.url), 'utf8');

/** URL `file://` de la página de práctica: la abren igual Selenium (`driver.get`) y Playwright (`page.goto`). */
export const fixtureUrl = (name: string) => new URL(`../fixtures/${name}.html`, import.meta.url).href;

export const locatorsHtml = fixture('locators');

/** Origen ficticio del sitio de práctica `fixtures/tienda`; nunca sale a la red. */
export const TIENDA = 'https://tienda.test';

const tiendaPages: Record<string, string> = {
	'/': 'index',
	'/productos': 'productos',
	'/login': 'login',
	'/cuenta': 'cuenta',
	'/cuenta/pedidos': 'cuenta',
};

/** Sirve `fixtures/tienda` en `https://tienda.test` interceptando las peticiones del contexto. */
export async function serveTienda(context: BrowserContext) {
	await context.route(`${TIENDA}/**`, (route) => {
		const page = tiendaPages[new URL(route.request().url()).pathname];
		return route.fulfill({
			status: page ? 200 : 404,
			contentType: 'text/html; charset=utf-8',
			body: fixture(`tienda/${page ?? 'no-encontrada'}`),
		});
	});
}

/**
 * API de pedidos de `snippets/server/api-de-practica.mjs`, un servidor HTTP real (las peticiones de
 * `request` no pasan por `route`). Lo arranca `webServer` en `playwright.config.ts`.
 */
export const API_PRACTICA = `http://127.0.0.1:${process.env.API_PRACTICA_PORT ?? 4789}`;
