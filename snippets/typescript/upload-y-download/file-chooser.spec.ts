import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('upload-y-download'));
});

// #region example
test('subir una foto con un botón personalizado', async ({ page }) => {
	// Empieza a esperar el diálogo antes del clic que lo abre (sin await todavía).
	const fileChooserPromise = page.waitForEvent('filechooser');
	await page.getByRole('button', { name: 'Subir foto' }).click();
	const fileChooser = await fileChooserPromise;

	await fileChooser.setFiles({ name: 'perfil.png', mimeType: 'image/png', buffer: Buffer.from('foto') });

	await expect(page.getByText('Foto: perfil.png')).toBeVisible();
});
// #endregion
