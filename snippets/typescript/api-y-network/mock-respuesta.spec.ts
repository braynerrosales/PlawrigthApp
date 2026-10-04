import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.ts';

test.use({ baseURL: API_PRACTICA });

// #region example
test('mostrar los pedidos que devuelve la API simulada', async ({ page }) => {
	await page.route('**/api/pedidos', (route) =>
		route.fulfill({
			json: [
				{ id: 101, cliente: 'Cliente simulado', producto: 'Mesa', cantidad: 1, estado: 'pendiente' },
				{ id: 102, cliente: 'Otro cliente', producto: 'Lámpara', cantidad: 5, estado: 'enviado' },
			],
		}),
	);

	await page.goto('/');

	await expect(page.getByRole('status')).toHaveText('2 pedidos');
	await expect(page.getByRole('row').filter({ hasText: 'Cliente simulado' })).toContainText('Mesa');
});
// #endregion
