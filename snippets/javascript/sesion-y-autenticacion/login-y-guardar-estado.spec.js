import { test, expect } from '@playwright/test';
import { PORTAL, servePortal } from './portal.js';

test.beforeEach(async ({ context }) => {
	await servePortal(context);
});

test.use({ baseURL: PORTAL });

// #region example
test('iniciar sesión una vez y guardar el estado', async ({ page, context }) => {
	await page.goto('/login');
	await page.getByLabel('Usuario').fill('ana');
	await page.getByLabel('Contraseña').fill('clave-de-prueba');
	await page.getByRole('button', { name: 'Ingresar' }).click();
	await expect(page.getByRole('heading', { name: 'Hola, Ana' })).toBeVisible();

	// Guarda cookies y localStorage en un archivo fuera del repositorio: contiene la sesión.
	const estado = await context.storageState({ path: test.info().outputPath('ana.json') });

	expect(estado.cookies).toContainEqual(expect.objectContaining({ name: 'sesion', httpOnly: true }));
	expect(estado.origins).toContainEqual({
		origin: PORTAL,
		localStorage: [{ name: 'ultimoUsuario', value: 'ana' }],
	});
});
// #endregion
