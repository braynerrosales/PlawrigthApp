import { test, expect } from '@playwright/test';
import { TIENDA, serveTienda } from '../support.js';

test.beforeEach(async ({ context }) => {
	await serveTienda(context);
});

test.use({ baseURL: TIENDA });

// #region example
test('volver atrás y adelante en el historial', async ({ page }) => {
	await page.goto('/');
	await page.getByRole('link', { name: 'Productos' }).click();
	await expect(page).toHaveURL(/\/productos$/);

	await page.goBack();
	await expect(page).toHaveURL('https://tienda.test/');
	await expect(page.getByRole('heading', { name: 'Bienvenido a Tienda QA' })).toBeVisible();

	await page.goForward();
	await expect(page).toHaveURL(/\/productos$/);
	await expect(page.getByRole('heading', { name: 'Productos' })).toBeVisible();
});
// #endregion
