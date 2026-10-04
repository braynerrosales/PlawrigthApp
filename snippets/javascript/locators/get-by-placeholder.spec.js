import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('getByPlaceholder localiza un campo sin etiqueta', async ({ page }) => {
	// #region example
	const search = page.getByPlaceholder('Buscar productos');
	await search.fill('teclado');

	await expect(search).toHaveValue('teclado');
	// #endregion
});
