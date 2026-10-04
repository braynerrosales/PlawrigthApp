import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

test('leer una celda por su posición', async ({ page }) => {
	// #region example
	// "Fila 3, columna 2": hoy es el cliente de PED-1003.
	const cliente = page.locator('tbody tr:nth-child(3) td:nth-child(2)');
	await expect(cliente).toHaveText('Elena Mora');
	// #endregion

	// Fuera del ejemplo: al ordenar por cliente, la misma posición es otro pedido.
	await page.getByRole('button', { name: 'Cliente' }).click();
	await expect(cliente).toHaveText('Carla Gómez');
});
