import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('filter acota una lista por texto o por contenido', async ({ page }) => {
	// #region example
	const keyboard = page.getByRole('listitem').filter({ hasText: 'Teclado mecánico' });
	await keyboard.getByRole('button', { name: 'Agregar al carrito' }).click();
	await expect(page.getByTestId('cart-count')).toHaveText('1');

	// has: el item debe contener otro Locator.
	const soldOut = page.getByRole('listitem').filter({
		has: page.getByText('Agotado', { exact: true }),
	});
	await expect(soldOut.getByRole('heading')).toHaveText('Monitor 4K');
	// #endregion
});
