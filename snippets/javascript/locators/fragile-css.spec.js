import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('CSS posicional: funciona hoy, se rompe con cualquier cambio de layout', async ({ page }) => {
	// #region example
	await page.locator('body > div:nth-child(2) > div:nth-child(4) > button').click();
	// #endregion
	await expect(page.locator('#settings-state')).toHaveText('Cambios guardados');
});
