import { test as base, expect } from '@playwright/test';
import { fixture } from '../support.ts';
import { RegistroPage } from './registro-page.ts';

// #region example
const test = base.extend<{ registroPage: RegistroPage }>({
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
