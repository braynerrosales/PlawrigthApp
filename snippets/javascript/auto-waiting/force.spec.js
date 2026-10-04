import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

test('force hace clic aunque el botón esté deshabilitado', async ({ page }) => {
	// #region example
	// "Pagar" está deshabilitado. force se salta las comprobaciones: el clic "pasa" y no hace nada.
	await page.getByRole('button', { name: 'Pagar' }).click({ force: true });
	// #endregion
	await expect(page.getByRole('status')).toBeEmpty();
});
