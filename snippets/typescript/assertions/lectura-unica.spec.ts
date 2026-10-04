import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

test('leer el texto una vez y compararlo falla con datos que llegan tarde', async ({ page }) => {
	const check = async () => {
		// #region example
		await page.getByRole('button', { name: 'Cargar pedidos' }).click();
		const texto = await page.getByRole('status').textContent();
		expect(texto).toBe('3 pedidos'); // falla: leyó «Cargando…» y no vuelve a intentarlo
		// #endregion
	};
	await expect(check()).rejects.toThrow();
});
