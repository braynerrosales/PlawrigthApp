import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('getByTestId localiza por un atributo de pruebas', async ({ page }) => {
	// #region example
	await expect(page.getByTestId('cart-count')).toHaveText('0');
	await expect(page.getByTestId('product-list').getByRole('listitem')).toHaveCount(3);
	// #endregion
});
