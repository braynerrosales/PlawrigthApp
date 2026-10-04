import { test, expect } from '@playwright/test';
import path from 'node:path';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('upload-y-download'));
});

// #region example
test('subir un archivo desde el disco', async ({ page }) => {
	// Ruta absoluta construida desde este archivo: no depende de dónde se ejecute la prueba.
	const factura = path.join(import.meta.dirname, '../../fixtures/archivos/factura.txt');

	await page.getByLabel('Adjuntos').setInputFiles(factura);

	const archivos = page.getByRole('list', { name: 'Archivos seleccionados' }).getByRole('listitem');
	await expect(archivos).toHaveText([/^factura\.txt \(\d+ bytes\)$/]);
});
// #endregion
