import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';
import { RegistroPage } from './registro-page.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
/** @type {import('./registro-page.js').DatosRegistro} */
const ana = { nombre: 'Ana Pérez', correo: 'ana@example.com', pais: 'Colombia', plan: 'Pro' };

test('registro de una cuenta Pro', async ({ page }) => {
	const registro = new RegistroPage(page);

	await registro.registrar(ana);

	await expect(registro.confirmacion).toHaveText('Cuenta Pro creada para Ana Pérez');
});

test('sin aceptar los términos no se puede crear la cuenta', async ({ page }) => {
	const registro = new RegistroPage(page);

	await registro.completar(ana);

	await expect(registro.crearCuenta).toBeDisabled();
});
// #endregion
