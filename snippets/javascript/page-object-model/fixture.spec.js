// @ts-nocheck -- en JavaScript, test.extend no da tipos a los fixtures nuevos; el ejemplo se escribe como en la
// documentación oficial (sin anotaciones) y se valida al ejecutarlo.
import { test as base, expect } from '@playwright/test';
import { fixture } from '../support.js';
import { RegistroPage } from './registro-page.js';

// #region example
const test = base.extend({
	registroPage: async ({ page }, use) => {
		// En tu proyecto: await page.goto('/registro');
		await page.setContent(fixture('acciones'));
		await use(new RegistroPage(page));
	},
});

test('registro de una cuenta Gratis', async ({ registroPage }) => {
	await registroPage.registrar({ nombre: 'Luis Gómez', correo: 'luis@example.com', pais: 'Chile', plan: 'Gratis' });

	await expect(registroPage.confirmacion).toHaveText('Cuenta Gratis creada para Luis Gómez');
});
// #endregion
