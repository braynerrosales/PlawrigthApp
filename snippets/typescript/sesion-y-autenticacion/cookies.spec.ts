import { test, expect } from '@playwright/test';
import { PORTAL, servePortal } from './portal.ts';

test.beforeEach(async ({ context }) => {
	await servePortal(context);
});

test.use({ baseURL: PORTAL });

// #region example
test('entrar con una cookie y perder la sesión al borrarla', async ({ page, context }) => {
	// Token ficticio de prueba: en un proyecto real lo entrega una API o un login previo.
	await context.addCookies([
		{ name: 'sesion', value: 'token-ficticio-ana', url: PORTAL, httpOnly: true, secure: true },
	]);

	await page.goto('/panel');
	await expect(page.getByRole('heading', { name: 'Hola, Ana' })).toBeVisible();

	await context.clearCookies({ name: 'sesion' });
	expect(await context.cookies(PORTAL)).toHaveLength(0);

	// Sin cookie, el panel redirige al login.
	await page.goto('/panel');
	await expect(page).toHaveURL(/\/login$/);
});
// #endregion
