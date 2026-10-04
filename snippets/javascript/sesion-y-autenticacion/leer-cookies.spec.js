import { test, expect } from '@playwright/test';
import { PORTAL, servePortal } from './portal.js';

test.beforeEach(async ({ context }) => {
	await servePortal(context);
});

test.use({ baseURL: PORTAL });

// #region example
test('la cookie de sesión es HttpOnly y Secure', async ({ page, context }) => {
	await page.goto('/login');
	await page.getByLabel('Usuario').fill('ana');
	await page.getByLabel('Contraseña').fill('clave-de-prueba');
	await page.getByRole('button', { name: 'Ingresar' }).click();
	await expect(page).toHaveURL(/\/panel$/);

	const cookies = await context.cookies(PORTAL);
	const sesion = cookies.find((cookie) => cookie.name === 'sesion');

	expect(sesion).toMatchObject({ httpOnly: true, secure: true, sameSite: 'Lax' });
	// El JavaScript de la página no puede leer una cookie HttpOnly; la prueba sí, desde el contexto.
	expect(await page.evaluate(() => document.cookie)).not.toContain('sesion=');
});
// #endregion
