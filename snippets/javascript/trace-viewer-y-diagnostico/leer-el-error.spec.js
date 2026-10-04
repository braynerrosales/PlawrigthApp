import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

test('una aserción que falla explica qué esperaba y qué encontró', async ({ page }) => {
	const prueba = async () => {
		// #region example
		// Falta un paso: nadie hizo clic en "Cargar pedidos", así que la lista sigue vacía.
		await expect(page.getByRole('list', { name: 'Pedidos' }).getByRole('listitem')).toHaveCount(3, { timeout: 2000 });
		// #endregion
	};
	await expect(prueba()).rejects.toThrow('toHaveCount');
});
