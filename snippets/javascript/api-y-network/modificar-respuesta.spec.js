import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.js';

test.use({ baseURL: API_PRACTICA });

// #region example
test('mostrar los pedidos como enviados', async ({ page }) => {
	await page.route('**/api/pedidos', async (route) => {
		const response = await route.fetch();
		const pedidos = await response.json();
		for (const pedido of pedidos) pedido.estado = 'enviado';
		await route.fulfill({ response, json: pedidos });
	});

	await page.goto('/');

	await expect(page.getByRole('row').filter({ hasText: 'Ana Torres' })).toContainText('enviado');
});
// #endregion
