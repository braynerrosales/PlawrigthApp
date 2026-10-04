import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

// #region example
test('el cliente agrega un producto disponible al carrito', async ({ page }) => {
	await page.getByLabel('Correo electrónico').fill('qa@example.com');
	await page.getByLabel('Contraseña').fill('clave-de-prueba');
	await page.getByRole('button', { name: 'Iniciar sesión' }).click();
	await expect(page.getByRole('status')).toHaveText('Bienvenido, qa@example.com');

	const product = page.getByRole('listitem').filter({ hasText: 'Mouse inalámbrico' }); // [!mark]
	await product.getByRole('button', { name: 'Agregar al carrito' }).click(); // [!mark]

	await expect(page.getByTestId('cart-count')).toHaveText('1');
});
// #endregion
