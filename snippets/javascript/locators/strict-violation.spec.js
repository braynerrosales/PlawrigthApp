import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('una acción sobre un Locator ambiguo falla por strictness', async ({ page }) => {
	const action = async () => {
		// #region example
		// Hay 3 botones con ese nombre: Playwright se niega a adivinar.
		await page.getByRole('button', { name: 'Agregar al carrito' }).click();
		// #endregion
	};
	await expect(action()).rejects.toThrow('strict mode violation');
});
