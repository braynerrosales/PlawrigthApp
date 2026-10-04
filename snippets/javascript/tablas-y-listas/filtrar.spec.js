import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

// #region example
test('filtrar y comprobar que solo quedan los pedidos esperados', async ({ page }) => {
	const filas = page.getByRole('table', { name: 'Pedidos' }).getByRole('row').filter({ has: page.getByRole('cell') });

	await page.getByLabel('Filtrar pedidos').fill('Pendiente');

	// La app aplica el filtro 300 ms después: las aserciones reintentan hasta verlo.
	await expect(filas).toHaveCount(3);
	await expect(filas).toHaveText([/PED-1003/, /PED-1006/, /PED-1009/]);
});
// #endregion
