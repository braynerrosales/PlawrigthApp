import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('iframes-y-dialogs'));
});

// #region example
test('el pago se completa dentro del iframe', async ({ page }) => {
	const pago = page.frameLocator('iframe[title="Pago con tarjeta"]');

	await pago.getByLabel('Número de tarjeta').fill('4111 1111 1111 1111');
	await pago.getByRole('button', { name: 'Pagar' }).click();

	await expect(pago.getByRole('status')).toHaveText('Pago aprobado');
});
// #endregion
