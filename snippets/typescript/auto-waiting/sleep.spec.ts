import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

// #region example
test('espera fija antes de actuar', async ({ page }) => {
	await page.waitForTimeout(1000); // "por si el aviso no se fue"
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();
	await page.waitForTimeout(2000); // "por si los datos tardan"

	await expect(page.getByRole('listitem')).toHaveCount(3);
});
// #endregion
