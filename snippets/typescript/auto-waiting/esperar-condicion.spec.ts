import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

// #region example
test('esperar el resultado, no el tiempo', async ({ page }) => {
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();

	await expect(page.getByRole('listitem')).toHaveCount(3);
});
// #endregion
