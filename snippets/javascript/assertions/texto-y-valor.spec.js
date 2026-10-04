import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('locators'));
});

// #region example
test('texto, valor y atributos', async ({ page }) => {
	await expect(page.getByTestId('cart-count')).toHaveText('0');
	await expect(page.getByText('Envío gratis')).toContainText('mayores a $50');

	const buscador = page.getByPlaceholder('Buscar productos');
	await expect(buscador).toHaveAttribute('type', 'search');
	await buscador.fill('teclado');
	await expect(buscador).toHaveValue('teclado');
});
// #endregion
