import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('XPath absoluto copiado de DevTools', async ({ page }) => {
	// #region example
	await page.locator('xpath=/html/body/div/div[4]/button').click();
	// #endregion
	await expect(page.locator('#settings-state')).toHaveText('Cambios guardados');
});
