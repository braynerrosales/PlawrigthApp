import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('formularios'));
});

test('enviar el formulario sin comprobar el resultado', async ({ page }) => {
	// #region example
	await page.getByLabel('Correo electrónico').fill('ana@correo');
	await page.getByLabel('Fecha de entrega').fill('2026-10-04');
	await page.getByRole('button', { name: 'Enviar solicitud' }).click();
	// Fin de la prueba: pasa en verde, aunque la app rechazó el correo.
	// #endregion

	// Fuera del ejemplo: demuestra que el envío en realidad falló.
	await expect(page.getByRole('alert')).toHaveText('Ingresa un correo válido');
});
