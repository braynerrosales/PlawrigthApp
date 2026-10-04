import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test('depurar subiendo esperas y timeouts (evitar)', async ({ page }) => {
	await page.setContent(fixture('pedidos'));
	// #region example
	// "Falló una vez en CI": se agrega una espera, se sube el timeout y se imprime el HTML.
	await page.waitForTimeout(1000);
	await page.getByRole('button', { name: 'Cargar pedidos' }).click({ timeout: 60_000 });
	console.log(await page.getByRole('status').textContent());
	await expect(page.getByRole('status')).toHaveText('3 pedidos', { timeout: 60_000 });
	// #endregion
});
