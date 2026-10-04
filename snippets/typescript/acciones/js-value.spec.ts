import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('asignar el valor con JavaScript no dispara eventos', async ({ page }) => {
	await page.getByLabel('Nombre').evaluate((el: HTMLInputElement) => (el.value = 'Ana Pérez'));
	await page.getByLabel('Correo electrónico').evaluate((el: HTMLInputElement) => (el.value = 'ana@example.com'));
	await page.getByLabel('Acepto los términos').check();

	// La app nunca recibió eventos input en los campos de texto: el botón sigue deshabilitado.
	await expect(page.getByRole('button', { name: 'Crear cuenta' })).toBeDisabled();
});
// #endregion
