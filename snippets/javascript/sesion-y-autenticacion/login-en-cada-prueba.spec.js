import { test, expect } from '@playwright/test';
import { PORTAL, servePortal } from './portal.js';

test.beforeEach(async ({ context }) => {
	await servePortal(context);
});

test.use({ baseURL: PORTAL });

// #region example
test.beforeEach(async ({ page }) => {
	// Cada prueba repite el formulario de login antes de hacer lo que de verdad quiere probar.
	await page.goto('/login');
	await page.getByLabel('Usuario').fill('ana');
	await page.getByLabel('Contraseña').fill('clave-de-prueba');
	await page.getByRole('button', { name: 'Ingresar' }).click();
	await expect(page).toHaveURL(/\/panel$/);
});

test('ver el saludo', async ({ page }) => {
	await expect(page.getByRole('heading', { name: 'Hola, Ana' })).toBeVisible();
});

test('ver el rol', async ({ page }) => {
	await expect(page.getByText('Rol: cliente')).toBeVisible();
});
// #endregion
