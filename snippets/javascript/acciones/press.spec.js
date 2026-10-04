import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('buscar con Enter', async ({ page }) => {
	const buscador = page.getByRole('searchbox', { name: 'Buscar en la ayuda' });

	await buscador.fill('facturas');
	await buscador.press('Enter');

	await expect(page.getByText('Resultados para «facturas»')).toBeVisible();
});
// #endregion
