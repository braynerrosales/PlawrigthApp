import { test, expect } from '@playwright/test';
import { fixtureUrl } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.goto(fixtureUrl('pedidos'));
});

// #region example
test('un Locator se vuelve a buscar en cada uso', async ({ page }) => {
	const cargar = page.getByRole('button', { name: 'Cargar pedidos' });
	const estado = page.getByRole('status');
	await cargar.click();
	await expect(estado).toHaveText('3 pedidos');

	const primero = page.getByRole('listitem').first();
	await expect(primero).toHaveText('PED-1001 · Pagado');

	// La página reemplaza la lista: el Locator encuentra el elemento nuevo.
	await cargar.click();
	await expect(estado).toHaveText('3 pedidos');
	await expect(primero).toHaveText('PED-1001 · Pagado'); // [!mark]
});
// #endregion
