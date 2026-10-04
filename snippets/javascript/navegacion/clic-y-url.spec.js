import { test, expect } from '@playwright/test';
import { TIENDA, serveTienda } from '../support.js';

test.beforeEach(async ({ context }) => {
	await serveTienda(context);
});

test.use({ baseURL: TIENDA });

// #region example
test('ir a productos desde el menú', async ({ page }) => {
	await page.goto('/');

	await page.getByRole('link', { name: 'Productos' }).click();

	await expect(page).toHaveURL(/\/productos$/);
	await expect(page.getByRole('heading', { name: 'Productos' })).toBeVisible();
});
// #endregion
