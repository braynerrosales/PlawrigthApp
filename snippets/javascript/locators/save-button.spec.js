import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('botón por rol y nombre: expresa la intención', async ({ page }) => {
	// #region example
	await page.getByRole('button', { name: 'Guardar' }).click();
	// #endregion
	await expect(page.getByText('Cambios guardados')).toBeVisible();
});
