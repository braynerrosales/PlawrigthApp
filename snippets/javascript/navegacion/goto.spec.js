import { test, expect } from '@playwright/test';
import { serveTienda } from '../support.js';

test.beforeEach(async ({ context }) => {
	await serveTienda(context);
});

// #region example
test.use({ baseURL: 'https://tienda.test' });

test('abrir la página de inicio', async ({ page }) => {
	// La ruta es relativa a baseURL: https://tienda.test/
	await page.goto('/');

	await expect(page.getByRole('heading', { name: 'Bienvenido a Tienda QA' })).toBeVisible();
});
// #endregion
