import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('iframes-y-dialogs'));
});

test('buscar el campo desde la página principal nunca lo encuentra', async ({ page }) => {
	const action = async () => {
		// #region example
		// El campo está dentro del iframe: desde `page` no existe, así que la espera nunca termina.
		await page.getByLabel('Número de tarjeta').fill('4111 1111 1111 1111', { timeout: 2000 });
		// #endregion
	};
	await expect(action()).rejects.toThrow('Timeout 2000ms exceeded');
});
