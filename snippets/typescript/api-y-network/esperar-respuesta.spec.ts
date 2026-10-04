import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.ts';

test.use({ baseURL: API_PRACTICA });

// #region example
test('crear un pedido desde el formulario', async ({ page }) => {
	await page.goto('/');
	await page.getByLabel('Cliente').fill('Prueba UI');
	await page.getByLabel('Producto').fill('Silla');
	await page.getByLabel('Cantidad').fill('4');

	const responsePromise = page.waitForResponse(
		(response) => response.url().endsWith('/api/pedidos') && response.request().method() === 'POST',
	);
	await page.getByRole('button', { name: 'Crear pedido' }).click();
	const response = await responsePromise;

	expect(response.status()).toBe(201);
	const { id } = await response.json();
	await expect(page.getByRole('alert')).toHaveText(`Pedido #${id} creado`);
});
// #endregion
