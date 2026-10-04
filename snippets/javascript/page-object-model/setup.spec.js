import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';
import { RegistroPage } from './registro-page.js';

// #region example
/** @type {RegistroPage} */
let registro;

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
	registro = new RegistroPage(page);
});

test('registro de una cuenta Gratis', async () => {
	await registro.registrar({ nombre: 'Luis Gómez', correo: 'luis@example.com', pais: 'Chile', plan: 'Gratis' });

	await expect(registro.confirmacion).toHaveText('Cuenta Gratis creada para Luis Gómez');
});
// #endregion
