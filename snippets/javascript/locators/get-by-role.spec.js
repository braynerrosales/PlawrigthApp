import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('getByRole localiza por rol y nombre accesible', async ({ page }) => {
	// #region example
	await page.getByRole('textbox', { name: 'Correo electrónico' }).fill('qa@example.com');
	await page.getByRole('checkbox', { name: 'Recordarme' }).check();
	await page.getByRole('button', { name: 'Iniciar sesión' }).click();

	await expect(page.getByRole('heading', { name: 'Productos', level: 2 })).toBeVisible();
	// #endregion
	await expect(page.getByRole('status')).toHaveText('Bienvenido, qa@example.com');
});
