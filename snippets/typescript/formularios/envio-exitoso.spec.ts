import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('formularios'));
});

// #region example
test('enviar con Enter y comprobar el resultado', async ({ page }) => {
	const correo = page.getByLabel('Correo electrónico');
	const fecha = page.getByLabel('Fecha de entrega');

	await fecha.fill('2026-10-04');
	await correo.fill('ana@example.com');
	// Enter dentro de un campo envía el formulario, como lo haría una persona.
	await correo.press('Enter');

	// El resultado visible y el estado posterior del formulario.
	await expect(page.getByRole('status')).toHaveText('Solicitud PED-1042 enviada');
	await expect(page.getByRole('alert')).toHaveCount(0);
	await expect(correo).toBeEmpty();
	await expect(fecha).toBeEmpty();
});
// #endregion
