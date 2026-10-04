import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

// #region example
test('la fila por su contenido sigue siendo la misma al ordenar', async ({ page }) => {
	const pedido = page.getByRole('row').filter({ hasText: 'PED-1003' });
	await expect(pedido).toContainText('Elena Mora');

	// La tabla se ordena y se vuelve a dibujar: la fila cambia de posición.
	await page.getByRole('button', { name: 'Cliente' }).click();

	// El locator se resuelve otra vez y encuentra la misma fila de datos.
	await expect(pedido).toContainText('Elena Mora');
});
// #endregion
