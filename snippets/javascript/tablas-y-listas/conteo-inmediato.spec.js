import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

test('contar una vez y recorrer con índices mientras la lista carga', async ({ page }) => {
	const movimientos = page.getByRole('list', { name: 'Últimos movimientos' }).getByRole('listitem');

	// #region example
	// count() no espera: la lista todavía está cargando y devuelve 0.
	const total = await movimientos.count();
	for (let i = 0; i < total; i++) {
		await expect(movimientos.nth(i)).toContainText('PED-');
	}
	// Fin de la prueba: pasa en verde sin haber comprobado ningún movimiento.
	// #endregion

	// Fuera del ejemplo: demuestra que el bucle no recorrió nada y que la lista sí tenía datos.
	expect(total).toBe(0);
	await expect(movimientos).toHaveCount(4);
});
