import { test, expect } from '@playwright/test';
import { API_PRACTICA } from '../support.js';

test.use({ baseURL: API_PRACTICA });

// #region example
test('cargar los pedidos sin imágenes', async ({ page }) => {
	await page.route('**/*.{png,jpg,jpeg,webp}', (route) => route.abort());

	await page.goto('/');

	await expect(page.getByText('Imagen no disponible')).toBeVisible();
	await expect(page.getByRole('row').filter({ hasText: 'Ana Torres' })).toBeVisible();
});
// #endregion
