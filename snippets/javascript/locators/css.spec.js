import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('CSS estable: ids y clases que el equipo controla', async ({ page }) => {
	// #region example
	const loginForm = page.locator('#login-form');
	await loginForm.getByLabel('Correo electrónico').fill('qa@example.com');

	await expect(page.locator('li.product-card')).toHaveCount(3);
	// #endregion
	await expect(loginForm.getByLabel('Correo electrónico')).toHaveValue('qa@example.com');
});
