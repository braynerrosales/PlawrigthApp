import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

// #region example
test('exportar pedidos', { tag: '@smoke' }, async ({ page }) => { // [!mark]
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();
	await page.getByRole('button', { name: 'Exportar' }).click();

	await expect(page.getByRole('status')).toHaveText('Exportación lista');
});
// #endregion
