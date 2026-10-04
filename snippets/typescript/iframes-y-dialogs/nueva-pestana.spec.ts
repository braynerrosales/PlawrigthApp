import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('iframes-y-dialogs'));
});

// #region example
test('los términos se leen en otra pestaña y se aceptan en la original', async ({ page }) => {
	const terminosPromise = page.waitForEvent('popup');
	await page.getByRole('button', { name: 'Ver términos y condiciones' }).click();
	const terminos = await terminosPromise;

	// Las dos pestañas están abiertas y se usan a la vez, sin «cambiar» a ninguna.
	await expect(terminos).toHaveTitle('Términos y condiciones');
	await expect(terminos.getByText('Versión vigente: octubre de 2026')).toBeVisible();
	await page.getByLabel('Acepto los términos y condiciones').check();
	expect(page.context().pages()).toHaveLength(2);

	await terminos.close();
	expect(page.context().pages()).toHaveLength(1);
	await expect(page.getByLabel('Acepto los términos y condiciones')).toBeChecked();
});
// #endregion
