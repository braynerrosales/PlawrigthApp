import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('pedidos'));
});

// #region example
test.describe.configure({ retries: 2 }); // [!mark]

test('exportar pedidos', async ({ page }, testInfo) => {
	// Simula un fallo intermitente: solo falla el primer intento.
	expect(testInfo.retry, 'fallo intermitente simulado').toBeGreaterThan(0); // [!mark]

	await page.getByRole('button', { name: 'Cargar pedidos' }).click();
	await page.getByRole('button', { name: 'Exportar' }).click();

	await expect(page.getByRole('status')).toHaveText('Exportación lista');
});
// #endregion
