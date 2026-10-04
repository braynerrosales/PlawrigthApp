import { rmSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { test, expect } from '@playwright/test';
import { PORTAL, saveSession, servePortal } from './portal.js';

// Hace el papel del proyecto `setup`: inicia sesión una vez por worker y guarda el estado fuera del repositorio.
const estadoDeAna = join(tmpdir(), `portal-qa-ana-${process.pid}.json`);

test.beforeAll(async ({ browser }) => {
	await saveSession(browser, 'ana', estadoDeAna);
});

test.afterAll(() => rmSync(estadoDeAna, { force: true }));

test.beforeEach(async ({ context }) => {
	await servePortal(context);
});

// #region example
test.use({ baseURL: PORTAL, storageState: estadoDeAna });

test('el panel abre directamente con la sesión guardada', async ({ page }) => {
	// Sin pasar por /login: el contexto ya trae la cookie de sesión.
	await page.goto('/panel');

	await expect(page).toHaveURL(/\/panel$/);
	await expect(page.getByRole('heading', { name: 'Hola, Ana' })).toBeVisible();
});

test('el cliente no ve la sección de administración', async ({ page }) => {
	await page.goto('/panel');

	await expect(page.getByText('Rol: cliente')).toBeVisible();
	await expect(page.getByRole('heading', { name: 'Administración' })).toBeHidden();
});
// #endregion
