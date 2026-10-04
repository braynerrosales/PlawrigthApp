import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.js';

test.use({ baseURL: API_PRACTICA });

// #region example
test('crear un pedido esperando un tiempo fijo', async ({ page }) => {
	await page.goto('/');
	await page.getByLabel('Cliente').fill('Prueba UI');
	await page.getByLabel('Producto').fill('Silla');
	await page.getByRole('button', { name: 'Crear pedido' }).click();

	// «Dos segundos deberían bastar»: sobra casi siempre y, el día que la API tarda más, falla.
	await page.waitForTimeout(2_000);
	const mensaje = await page.getByRole('alert').textContent();
	expect(mensaje).toContain('creado');
});
// #endregion
