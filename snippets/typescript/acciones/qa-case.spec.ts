import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('registro de una cuenta Pro', async ({ page }) => {
	await page.getByLabel('Nombre').fill('Ana Pérez');
	await page.getByLabel('Correo electrónico').fill('ana@example.com');
	await page.getByLabel('País').selectOption({ label: 'Colombia' });
	await page.getByRole('radio', { name: 'Pro' }).check();
	await page.getByLabel('Acepto los términos').check();

	await page.getByRole('button', { name: 'Crear cuenta' }).click();

	await expect(page.getByRole('status')).toHaveText('Cuenta Pro creada para Ana Pérez');
});
// #endregion
