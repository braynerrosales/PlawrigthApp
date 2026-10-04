import { readFileSync } from 'node:fs';

/**
 * Página de práctica de `snippets/fixtures`, compartida con los snippets de C# y TypeScript.
 * @param {string} name
 */
export const fixture = (name) => readFileSync(new URL(`../fixtures/${name}.html`, import.meta.url), 'utf8');

export const locatorsHtml = fixture('locators');

/** Origen ficticio del sitio de práctica `fixtures/tienda`; nunca sale a la red. */
export const TIENDA = 'https://tienda.test';

/** @type {Record<string, string>} */
const tiendaPages = {
	'/': 'index',
	'/productos': 'productos',
	'/login': 'login',
	'/cuenta': 'cuenta',
	'/cuenta/pedidos': 'cuenta',
};

/**
 * Sirve `fixtures/tienda` en `https://tienda.test` interceptando las peticiones del contexto.
 * @param {import('@playwright/test').BrowserContext} context
 */
export async function serveTienda(context) {
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
