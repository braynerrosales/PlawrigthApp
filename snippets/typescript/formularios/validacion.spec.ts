import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('formularios'));
});

// #region example
test('un correo inválido muestra el error de la app', async ({ page }) => {
	const correo = page.getByLabel('Correo electrónico');

	await correo.fill('ana@correo');
	await page.getByLabel('Fecha de entrega').fill('2026-10-04');
	await page.getByRole('button', { name: 'Enviar solicitud' }).click();

	// El mensaje es de la app, no del navegador: es estable y se puede comprobar.
	await expect(page.getByRole('alert')).toHaveText('Ingresa un correo válido');
	await expect(correo).toHaveAttribute('aria-invalid', 'true');
	await expect(page.getByRole('status')).toBeEmpty();
});
// #endregion
