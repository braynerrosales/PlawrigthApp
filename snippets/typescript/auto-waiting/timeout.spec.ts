import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

test('una acción que nunca puede ejecutarse termina en timeout', async ({ page }) => {
	const action = async () => {
		// #region example
		// "Pagar" nunca se habilita: Playwright espera hasta el timeout y explica qué faltó.
		await page.getByRole('button', { name: 'Pagar' }).click({ timeout: 2000 });
		// #endregion
	};
	await expect(action()).rejects.toThrow('Timeout 2000ms exceeded');
});
