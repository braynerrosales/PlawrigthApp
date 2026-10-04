import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test('marca dónde detenerse al depurar', async ({ page }) => {
	await page.setContent(fixture('pedidos'));
	// #region example
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();

	// Descomenta solo mientras depuras y ejecuta en modo headed (--debug o PWDEBUG=1).
	// Nunca lo subas al repositorio: en CI la prueba quedaría detenida hasta el timeout.
	// await page.pause();

	await expect(page.getByRole('status')).toHaveText('3 pedidos');
	// #endregion
});
