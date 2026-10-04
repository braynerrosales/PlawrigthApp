import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('upload-y-download'));
});

// #region example
test('subir varios archivos creados en memoria y quitarlos', async ({ page }) => {
	const adjuntos = page.getByLabel('Adjuntos');
	const archivos = page.getByRole('list', { name: 'Archivos seleccionados' }).getByRole('listitem');

	await adjuntos.setInputFiles([
		{ name: 'notas.txt', mimeType: 'text/plain', buffer: Buffer.from('Entregar por la tarde') },
		{ name: 'datos.csv', mimeType: 'text/csv', buffer: Buffer.from('id,total\n1,120.00\n') },
	]);
	await expect(archivos).toHaveText(['notas.txt (21 bytes)', 'datos.csv (18 bytes)']);

	// Un arreglo vacío deja el input sin archivos.
	await adjuntos.setInputFiles([]);
	await expect(archivos).toHaveCount(0);
});
// #endregion
