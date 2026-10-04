import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('getByLabel localiza campos de formulario por su etiqueta', async ({ page }) => {
	// #region example
	await page.getByLabel('Correo electrónico').fill('qa@example.com');
	await page.getByLabel('Contraseña').fill('clave-de-prueba');

	await expect(page.getByLabel('Contraseña')).toHaveValue('clave-de-prueba');
	// #endregion
});
