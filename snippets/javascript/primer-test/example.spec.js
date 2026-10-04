// #region example
import { test, expect } from '@playwright/test';

test('tiene título', async ({ page }) => {
	await page.goto('https://playwright.dev/');

	await expect(page).toHaveTitle(/Playwright/);
});

test('link Get started', async ({ page }) => {
	await page.goto('https://playwright.dev/');

	await page.getByRole('link', { name: 'Get started' }).click();

	await expect(page.getByRole('heading', { name: 'Installation' })).toBeVisible();
});
// #endregion
