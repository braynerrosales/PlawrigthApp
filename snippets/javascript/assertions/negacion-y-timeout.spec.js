import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

// #region example
test('negación y timeout propio', async ({ page }) => {
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();

	await expect(page.getByText('Cargando…')).not.toBeVisible();
	await expect(page.getByRole('button', { name: 'Exportar' })).toBeEnabled({ timeout: 10_000 });
});
// #endregion
