import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('locators'));
});

// #region example
test('título de la página', async ({ page }) => {
	await expect(page).toHaveTitle('Tienda QA');
});
// #endregion
