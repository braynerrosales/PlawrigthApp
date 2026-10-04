import { test, expect } from '@playwright/test';
import { serveTienda } from '../support.js';

test.beforeEach(async ({ context }) => {
	await serveTienda(context);
});

// #region example
test('ver el catálogo', async ({ page }) => {
	await page.goto('https://tienda.test/productos');
	await expect(page.getByRole('heading', { name: 'Productos' })).toBeVisible();
});

test('abrir el inicio de sesión', async ({ page }) => {
	// La misma URL completa, repetida en cada prueba: cambiar de ambiente obliga a editarlas todas.
	await page.goto('https://tienda.test/login');
	await expect(page.getByRole('heading', { name: 'Iniciar sesión' })).toBeVisible();
});
// #endregion
