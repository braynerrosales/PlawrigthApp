import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('upload-y-download'));
});

test('esperar la descarga después del clic llega tarde', async ({ page }) => {
	const action = async () => {
		// #region example
		await page.getByRole('button', { name: 'Exportar CSV' }).click();
		// La descarga ya empezó durante el clic: esta espera no la ve y vence.
		const download = await page.waitForEvent('download', { timeout: 2000 });
		// #endregion
		return download;
	};
	await expect(action()).rejects.toThrow('Timeout 2000ms exceeded');
});
