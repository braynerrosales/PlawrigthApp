import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';
import { RegistroPage } from './registro-page.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
const ana = { nombre: 'Ana Pérez', correo: 'ana@example.com', pais: 'Colombia', plan: 'Pro' } as const;

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
