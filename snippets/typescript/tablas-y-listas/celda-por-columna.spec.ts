import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

// #region example
test('leer el estado de un pedido por el nombre de la columna', async ({ page }) => {
	const tabla = page.getByRole('table', { name: 'Pedidos' });

	// El índice sale del encabezado: si se agrega o se mueve una columna, se recalcula.
	const encabezados = await tabla.getByRole('columnheader').allTextContents();
	const columnaEstado = encabezados.indexOf('Estado');

	// `has` + `exact`: la fila cuya celda es exactamente PED-1003.
	const fila = tabla.getByRole('row').filter({
		has: page.getByRole('cell', { name: 'PED-1003', exact: true }),
	});
	await expect(fila.getByRole('cell').nth(columnaEstado)).toHaveText('Pendiente');
});
// #endregion
