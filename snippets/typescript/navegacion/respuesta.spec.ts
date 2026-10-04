import { test, expect } from '@playwright/test';
import { TIENDA, serveTienda } from '../support.ts';

test.beforeEach(async ({ context }) => {
	await serveTienda(context);
});

test.use({ baseURL: TIENDA });

// #region example
test('una ruta inexistente responde 404 sin lanzar excepción', async ({ page }) => {
	// goto devuelve la respuesta del documento principal y no falla por un 404 o un 500.
	const response = await page.goto('/no-existe');

	expect(response?.status()).toBe(404);
	expect(response?.ok()).toBe(false);
	await expect(page.getByRole('heading', { name: 'Página no encontrada' })).toBeVisible();
});
// #endregion
