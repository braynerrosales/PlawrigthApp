import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('XPath por contenido, no por posición', async ({ page }) => {
	// #region example
	const keyboardPrice = page.locator("xpath=//li[h3[normalize-space()='Teclado mecánico']]/p");

	await expect(keyboardPrice).toHaveText('$45');
	// #endregion
});
