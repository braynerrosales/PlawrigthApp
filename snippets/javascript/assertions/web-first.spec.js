import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

// #region example
test('la aserción espera a que lleguen los datos', async ({ page }) => {
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();

	await expect(page.getByRole('status')).toHaveText('3 pedidos');
});
// #endregion
