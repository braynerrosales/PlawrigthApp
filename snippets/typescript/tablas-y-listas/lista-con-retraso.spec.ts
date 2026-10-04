import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

// #region example
test('esperar una lista que se carga con retraso', async ({ page }) => {
	const movimientos = page.getByRole('list', { name: 'Últimos movimientos' }).getByRole('listitem');

	// Reintenta hasta que la lista tiene exactamente estos elementos, en este orden.
	await expect(movimientos).toHaveText(['PED-1003 pagado', 'PED-1007 enviado', 'PED-1001 entregado', 'PED-1005 cancelado']);

	// Leer los textos solo cuando necesitas los datos, y después de la aserción: allTextContents no espera.
	const textos = await movimientos.allTextContents();
	expect(textos.filter((texto) => texto.endsWith('cancelado'))).toEqual(['PED-1005 cancelado']);
});
// #endregion
