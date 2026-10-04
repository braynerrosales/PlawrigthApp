import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('mostrar un tooltip', async ({ page }) => {
	await page.getByRole('button', { name: 'Más información' }).hover();

	await expect(page.getByRole('tooltip')).toHaveText('Respondemos en menos de 24 horas');
});
// #endregion
