import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('locators'));
});

// #region example
test('revisar varias cosas aunque una falle', async ({ page }) => {
	await expect.soft(page.getByTestId('cart-count')).toHaveText('0');
	await expect.soft(page.getByRole('heading', { name: 'Productos' })).toBeVisible();
	await expect.soft(page.getByRole('button', { name: 'Guardar' })).toBeEnabled();
});
// #endregion
