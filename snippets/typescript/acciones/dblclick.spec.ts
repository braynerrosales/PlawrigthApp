import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('renombrar con doble clic', async ({ page }) => {
	await page.getByText('Mi lista').dblclick();

	const nombre = page.getByRole('textbox', { name: 'Nombre de la lista' });
	await expect(nombre).toBeFocused();
	await nombre.fill('Pruebas de regresión');
	await expect(nombre).toHaveValue('Pruebas de regresión');
});
// #endregion
