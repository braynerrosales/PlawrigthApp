import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('iframes-y-dialogs'));
});

// #region example
test('aceptar el confirm elimina la tarea', async ({ page }) => {
	const tarea = page.getByRole('listitem').filter({ hasText: 'Revisar reporte' });

	// El handler se registra ANTES de la acción que abre el diálogo.
	let mensaje = '';
	page.once('dialog', async (dialog) => {
		mensaje = dialog.message();
		await dialog.accept();
	});
	await tarea.getByRole('button', { name: 'Eliminar' }).click();

	await expect(tarea).toBeHidden();
	expect(mensaje).toBe('¿Eliminar «Revisar reporte»?');
});
// #endregion
