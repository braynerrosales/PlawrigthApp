import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('formularios'));
});

// #region example
test('completar un campo de fecha', async ({ page }) => {
	const fecha = page.getByLabel('Fecha de entrega');

	// Siempre en formato ISO (yyyy-mm-dd), sin importar cómo lo muestre el navegador.
	await fecha.fill('2026-10-04');

	await expect(fecha).toHaveValue('2026-10-04');
	await expect(page.getByText('Entrega: 04/10/2026')).toBeVisible();
});
// #endregion
