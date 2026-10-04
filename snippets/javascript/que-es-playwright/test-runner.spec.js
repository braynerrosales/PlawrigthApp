// #region example
import { test, expect } from '@playwright/test';

test('Playwright Test entrega la page lista', async ({ page }) => {
	await page.goto('https://playwright.dev/');

	await expect(page).toHaveTitle(/Playwright/);
});
// #endregion
