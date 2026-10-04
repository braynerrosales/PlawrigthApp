import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('registro de una cuenta Gratis', async ({ page }) => {
	await page.getByLabel('Nombre').fill('Ana Pérez');
	await page.getByLabel('Correo electrónico').fill('ana@example.com');
	await page.getByLabel('Acepto los términos').check();
	await page.getByRole('button', { name: 'Crear cuenta' }).click();

	await expect(page.getByRole('status')).toHaveText('Cuenta Gratis creada para Ana Pérez');
});

test('sin aceptar los términos no se puede crear la cuenta', async ({ page }) => {
	await page.getByLabel('Nombre').fill('Ana Pérez');
	await page.getByLabel('Correo electrónico').fill('ana@example.com');

	await expect(page.getByRole('button', { name: 'Crear cuenta' })).toBeDisabled();
});
// #endregion
