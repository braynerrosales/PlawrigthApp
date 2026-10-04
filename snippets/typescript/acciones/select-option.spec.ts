import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('elegir una opción por su texto', async ({ page }) => {
	const pais = page.getByLabel('País');

	await pais.selectOption({ label: 'Chile' });

	await expect(pais).toHaveValue('cl');
});
// #endregion
