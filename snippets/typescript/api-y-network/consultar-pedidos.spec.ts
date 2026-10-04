import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.ts';

test.use({ baseURL: API_PRACTICA });

// #region example
test('consultar un pedido por la API', async ({ request }) => {
	const response = await request.get('/api/pedidos/1');

	await expect(response).toBeOK();
	const pedido = await response.json();
	expect(pedido).toMatchObject({ id: 1, cliente: 'Ana Torres', producto: 'Teclado' });
});
// #endregion
