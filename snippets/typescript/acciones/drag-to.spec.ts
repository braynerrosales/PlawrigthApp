import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('acciones'));
});

// #region example
test('mover una tarea a Hecho', async ({ page }) => {
	const tarea = page.getByRole('list', { name: 'Pendiente' }).getByText('Revisar reporte');
	const hecho = page.getByRole('list', { name: 'Hecho' });

	await tarea.dragTo(hecho);

	await expect(hecho.getByRole('listitem')).toHaveText(['Revisar reporte']);
});
// #endregion
