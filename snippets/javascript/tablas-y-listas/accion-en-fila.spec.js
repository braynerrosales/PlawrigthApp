import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

// #region example
test('cancelar un pedido desde su fila', async ({ page }) => {
	// La fila se identifica por un dato que el usuario reconoce, no por su posición.
	const fila = page.getByRole('row').filter({ hasText: 'PED-1003' });

	// Todas las filas tienen un botón "Cancelar": se busca dentro de la fila.
	await fila.getByRole('button', { name: 'Cancelar' }).click();

	await expect(fila).toContainText('Cancelado');
	await expect(fila.getByRole('button', { name: 'Cancelar' })).toBeDisabled();
});
// #endregion
