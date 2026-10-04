import { test, expect } from '@playwright/test';
import { TIENDA, serveTienda } from '../support.ts';

test.beforeEach(async ({ context }) => {
	await serveTienda(context);
});

test.use({ baseURL: TIENDA });

// #region example
test('el login redirige a la cuenta', async ({ page }) => {
	await page.goto('/login');
	await page.getByLabel('Usuario').fill('ana');
	await page.getByLabel('Contraseña').fill('clave-de-prueba');
	await page.getByRole('button', { name: 'Ingresar' }).click();

	// La redirección ocurre un momento después del clic: toHaveURL reintenta hasta que la URL coincide.
	await expect(page).toHaveURL(/\/cuenta$/);
	await expect(page.getByRole('heading', { name: 'Mi cuenta' })).toBeVisible();
});
// #endregion
