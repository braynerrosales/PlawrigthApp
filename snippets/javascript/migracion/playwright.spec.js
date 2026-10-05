import { test, expect } from '@playwright/test';
import { fixtureUrl } from '../support.js';

// #region example
test('exportar pedidos', async ({ page }) => {
	await page.goto(fixtureUrl('pedidos'));
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();
	await page.getByRole('button', { name: 'Exportar' }).click();

	await expect(page.getByRole('status')).toHaveText('Exportación lista');
	await expect(page.getByRole('listitem')).toHaveCount(3);
});
// #endregion
