import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('marcar checkboxes y radios', async ({ page }) => {
	await page.getByLabel('Acepto los términos').check();
	await page.getByLabel('Recibir novedades').uncheck();
	await page.getByRole('radio', { name: 'Pro' }).check();

	await expect(page.getByLabel('Acepto los términos')).toBeChecked();
	await expect(page.getByLabel('Recibir novedades')).not.toBeChecked();
	await expect(page.getByRole('radio', { name: 'Gratis' })).not.toBeChecked();
});
// #endregion
