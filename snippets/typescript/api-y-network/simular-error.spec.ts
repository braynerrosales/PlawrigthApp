import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.ts';

test.use({ baseURL: API_PRACTICA });

// #region example
test('avisar cuando la API falla', async ({ page }) => {
	await page.route('**/api/pedidos', (route) =>
		route.fulfill({ status: 500, json: { errores: ['error interno'] } }),
	);

	await page.goto('/');

	await expect(page.getByRole('status')).toHaveText('No se pudieron cargar los pedidos. Inténtalo de nuevo más tarde.');
	await expect(page.getByRole('table')).toBeHidden();
});
// #endregion
