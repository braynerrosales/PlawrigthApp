import { test, expect } from '@playwright/test';
import { readFile } from 'node:fs/promises';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('upload-y-download'));
});

// #region example
test('descargar el CSV y comprobar su contenido', async ({ page }) => {
	// Empieza a esperar antes del clic (sin await) y recoge el resultado después.
	const downloadPromise = page.waitForEvent('download');
	await page.getByRole('button', { name: 'Exportar CSV' }).click();
	const download = await downloadPromise;

	expect(download.suggestedFilename()).toBe('pedidos.csv');

	// path() espera a que termine la descarga; el archivo se borra al cerrar el contexto.
	const csv = await readFile(await download.path(), 'utf8');
	expect(csv.split('\n')[0]).toBe('id,cliente,total');
	expect(csv).toContain('1,Ana Pérez,120.00');
});
// #endregion
