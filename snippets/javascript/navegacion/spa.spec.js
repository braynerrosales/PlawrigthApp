import { test, expect } from '@playwright/test';
import { TIENDA, serveTienda } from '../support.js';

test.beforeEach(async ({ context }) => {
	await serveTienda(context);
});

test.use({ baseURL: TIENDA });

// #region example
test('navegar dentro de una SPA', async ({ page }) => {
	await page.goto('/cuenta');
	await expect(page.getByRole('heading', { name: 'Perfil' })).toBeVisible();

	// La app cambia la URL con history.pushState: no se carga un documento nuevo.
	await page.getByRole('link', { name: 'Pedidos' }).click();
	await expect(page).toHaveURL(/\/cuenta\/pedidos$/);
	await expect(page.getByRole('heading', { name: 'Pedidos' })).toBeVisible();

	// Atrás también funciona: la app escucha popstate y vuelve a mostrar Perfil.
	await page.goBack();
	await expect(page).toHaveURL(/\/cuenta$/);
	await expect(page.getByRole('heading', { name: 'Perfil' })).toBeVisible();
});
// #endregion
