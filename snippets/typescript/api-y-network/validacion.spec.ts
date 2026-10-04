import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.ts';

test.use({ baseURL: API_PRACTICA });

// #region example
test('rechazar un pedido sin cantidad válida', async ({ request }) => {
	const response = await request.post('/api/pedidos', {
		data: { cliente: 'Prueba API', producto: 'Webcam', cantidad: 0 },
	});

	await expect(response).not.toBeOK();
	expect(response.status()).toBe(400);
	expect(await response.json()).toEqual({ errores: ['cantidad debe ser un entero mayor que 0'] });
});
// #endregion
