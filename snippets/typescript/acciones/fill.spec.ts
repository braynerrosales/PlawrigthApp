import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('completar y limpiar campos', async ({ page }) => {
	const nombre = page.getByLabel('Nombre');

	await nombre.fill('Ana Pérez');
	await expect(nombre).toHaveValue('Ana Pérez');

	await nombre.clear();
	await expect(nombre).toHaveValue('');
});
// #endregion
