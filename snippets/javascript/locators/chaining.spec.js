import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('encadenar Locators acota la búsqueda a una región', async ({ page }) => {
	// #region example
	const productList = page.getByTestId('product-list');
	const mouseCard = productList.getByRole('listitem').filter({ hasText: 'Mouse inalámbrico' });

	await mouseCard.getByRole('button', { name: 'Agregar al carrito' }).click();

	await expect(page.getByTestId('cart-count')).toHaveText('1');
	// #endregion
});
