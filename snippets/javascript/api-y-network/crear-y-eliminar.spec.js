import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.js';

test.use({ baseURL: API_PRACTICA });

// #region example
test('crear, leer y eliminar un pedido', async ({ request }) => {
	const creado = await request.post('/api/pedidos', {
		data: { cliente: 'Prueba API', producto: 'Webcam', cantidad: 2 },
	});
	expect(creado.status()).toBe(201);
	const { id } = await creado.json();

	const leido = await request.get(`/api/pedidos/${id}`);
	await expect(leido).toBeOK();
	expect(await leido.json()).toMatchObject({ cliente: 'Prueba API', cantidad: 2, estado: 'pendiente' });

	const eliminado = await request.delete(`/api/pedidos/${id}`);
	expect(eliminado.status()).toBe(204);
	expect((await request.get(`/api/pedidos/${id}`)).status()).toBe(404);
});
// #endregion
