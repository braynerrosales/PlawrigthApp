import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test('nombra los pasos de la prueba', async ({ page }) => {
	// #region example
	await test.step('Abrir la pantalla de pedidos', async () => {
		await page.setContent(fixture('pedidos'));
	});
	await test.step('Cargar los pedidos', async () => {
		await page.getByRole('button', { name: 'Cargar pedidos' }).click();
		await expect(page.getByRole('status')).toHaveText('3 pedidos');
	});
	await test.step('Exportar', async () => {
		await page.getByRole('button', { name: 'Exportar' }).click();
		await expect(page.getByRole('status')).toHaveText('Exportación lista');
	});
	// #endregion
});
