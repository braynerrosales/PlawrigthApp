import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

// #region example
test('ordenar por cliente y comprobar el orden', async ({ page }) => {
	const tabla = page.getByRole('table', { name: 'Pedidos' });
	// Solo las filas con celdas de datos: la fila del encabezado tiene columnheader, no cell.
	const filas = tabla.getByRole('row').filter({ has: page.getByRole('cell') });

	await tabla.getByRole('button', { name: 'Cliente' }).click();

	await expect(tabla.getByRole('columnheader', { name: 'Cliente' })).toHaveAttribute('aria-sort', 'ascending');
	// Un arreglo comprueba la cantidad de filas y el texto de cada una, en orden.
	await expect(filas).toHaveText([/Ana Torres/, /Bruno Ríos/, /Carla Gómez/, /Diego Ruiz/, /Elena Mora/]);
});
// #endregion
