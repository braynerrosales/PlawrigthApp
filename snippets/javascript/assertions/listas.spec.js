import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('locators'));
});

// #region example
test('cantidad y contenido de una lista', async ({ page }) => {
	const productos = page.getByTestId('product-list').getByRole('listitem');

	await expect(productos).toHaveCount(3);
	await expect(productos.getByRole('heading')).toHaveText(['Teclado mecánico', 'Mouse inalámbrico', 'Monitor 4K']);
});
// #endregion
