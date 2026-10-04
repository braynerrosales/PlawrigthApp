import { rmSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { test, expect } from '@playwright/test';
import { PORTAL, saveSession, servePortal } from './portal.ts';

// Fuera del repositorio: los archivos contienen las sesiones.
const estadoDeAdmin = join(tmpdir(), `portal-qa-roles-admin-${process.pid}.json`);
const estadoDeAna = join(tmpdir(), `portal-qa-roles-ana-${process.pid}.json`);

test.beforeAll(async ({ browser }) => {
	await saveSession(browser, 'admin', estadoDeAdmin);
	await saveSession(browser, 'ana', estadoDeAna);
});

test.afterAll(() => {
	rmSync(estadoDeAdmin, { force: true });
	rmSync(estadoDeAna, { force: true });
});

// #region example
test('admin y cliente en la misma prueba', async ({ browser }) => {
	const adminContext = await browser.newContext({ baseURL: PORTAL, storageState: estadoDeAdmin });
	const clienteContext = await browser.newContext({ baseURL: PORTAL, storageState: estadoDeAna });
	await servePortal(adminContext);
	await servePortal(clienteContext);

	const adminPage = await adminContext.newPage();
	const clientePage = await clienteContext.newPage();
	await adminPage.goto('/panel');
	await clientePage.goto('/panel');

	await expect(adminPage.getByRole('heading', { name: 'Administración' })).toBeVisible();
	await expect(clientePage.getByRole('heading', { name: 'Hola, Ana' })).toBeVisible();
	await expect(clientePage.getByRole('heading', { name: 'Administración' })).toBeHidden();

	await adminContext.close();
	await clienteContext.close();
});
// #endregion
