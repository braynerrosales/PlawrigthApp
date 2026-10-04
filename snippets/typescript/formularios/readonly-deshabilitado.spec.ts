import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('formularios'));
});

// #region example
test('campos de solo lectura y deshabilitados', async ({ page }) => {
	const pedido = page.getByLabel('Número de pedido');
	const codigo = page.getByLabel('Código de descuento');

	// readonly: se ve y se envía, pero no se puede editar.
	await expect(pedido).toHaveValue('PED-1042');
	await expect(pedido).not.toBeEditable();

	// disabled: la app lo habilita solo cuando marcas la casilla.
	await expect(codigo).toBeDisabled();
	await page.getByLabel('Tengo un cupón').check();
	await expect(codigo).toBeEnabled();
	await codigo.fill('QA10');
	await expect(codigo).toHaveValue('QA10');
});
// #endregion
