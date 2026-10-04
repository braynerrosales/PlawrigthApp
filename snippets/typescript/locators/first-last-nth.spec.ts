import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('first, last y nth eligen por posición dentro de las coincidencias', async ({ page }) => {
	// #region example
	const addButtons = page.getByRole('button', { name: 'Agregar al carrito' });
	await expect(addButtons).toHaveCount(3);

	await addButtons.first().click(); // índice 0
	await addButtons.nth(1).click(); // índice 1: nth empieza en cero
	await expect(addButtons.last()).toBeDisabled();
	// #endregion
	await expect(page.getByTestId('cart-count')).toHaveText('2');
});
