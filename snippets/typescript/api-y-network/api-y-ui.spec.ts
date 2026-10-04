import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.ts';

test.use({ baseURL: API_PRACTICA });

// #region example
test('un pedido creado por API aparece en la tabla', async ({ page }) => {
	const cliente = `Cliente ${Date.now()}`;
	const response = await page.request.post('/api/pedidos', {
		data: { cliente, producto: 'Auriculares', cantidad: 1 },
	});
	expect(response.status()).toBe(201);

	await page.goto('/');

	const fila = page.getByRole('row').filter({ hasText: cliente });
	await expect(fila).toContainText('Auriculares');
});
// #endregion
