import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('iframes-y-dialogs'));
});

// #region example
test('responder al prompt renombra la tarea', async ({ page }) => {
	const tareas = page.getByRole('list', { name: 'Tareas' });

	page.once('dialog', async (dialog) => {
		// accept con texto es la respuesta que escribiría la persona en el prompt.
		await dialog.accept('Revisar reporte final');
	});
	await tareas
		.getByRole('listitem')
		.filter({ hasText: 'Revisar reporte' })
		.getByRole('button', { name: 'Renombrar' })
		.click();

	await expect(tareas.getByRole('listitem').first()).toContainText('Revisar reporte final');
});
// #endregion
