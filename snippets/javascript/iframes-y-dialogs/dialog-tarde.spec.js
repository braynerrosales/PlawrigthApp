import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('iframes-y-dialogs'));
});

// #region example
test('registrar el handler después del clic llega tarde', async ({ page }) => {
	const tarea = page.getByRole('listitem').filter({ hasText: 'Revisar reporte' });

	await tarea.getByRole('button', { name: 'Eliminar' }).click();
	// Demasiado tarde: sin listener, Playwright ya descartó el confirm (confirm devolvió false).
	page.once('dialog', (dialog) => dialog.accept());

	// La tarea no se eliminó.
	await expect(tarea).toBeVisible();
});
// #endregion
