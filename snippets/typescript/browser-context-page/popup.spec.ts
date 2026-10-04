import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
	await page.setContent(
		`<button onclick="window.open('').document.write('<h1>Ayuda</h1>')">Abrir ayuda</button>`,
	);
});

// #region example
test('la ayuda se abre en una ventana nueva', async ({ page }) => {
	const popupPromise = page.waitForEvent('popup');
	await page.getByRole('button', { name: 'Abrir ayuda' }).click();
	const popup = await popupPromise;

	await expect(popup.getByRole('heading', { name: 'Ayuda' })).toBeVisible();

	await popup.close();
	await expect(page.getByRole('button', { name: 'Abrir ayuda' })).toBeVisible();
});
// #endregion
