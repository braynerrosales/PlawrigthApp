import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('autocompletar tecla por tecla', async ({ page }) => {
	// El autocompletado escucha eventos de teclado; fill() no los genera.
	await page.getByLabel('Ciudad').pressSequentially('Bue');

	const sugerencias = page.getByRole('listbox', { name: 'Sugerencias' });
	await expect(sugerencias.getByRole('option')).toHaveText(['Buenos Aires']);
});
// #endregion
