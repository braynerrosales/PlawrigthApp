import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test('adjunta una captura como evidencia al reporte', async ({ page }) => {
	await page.setContent(fixture('pedidos'));
	await page.getByRole('button', { name: 'Cargar pedidos' }).click();
	await expect(page.getByRole('status')).toHaveText('3 pedidos');
	// #region example
	await test.info().attach('pedidos cargados', {
		body: await page.screenshot({ fullPage: true }),
		contentType: 'image/png',
	});
	// #endregion
	expect(test.info().attachments.map((a) => a.name)).toContain('pedidos cargados');
});
