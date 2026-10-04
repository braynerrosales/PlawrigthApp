import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('locators'));
});

// #region example
test('visibilidad y estado', async ({ page }) => {
	const monitor = page.getByRole('listitem').filter({ hasText: 'Monitor 4K' });

	await expect(page.getByRole('heading', { name: 'Productos' })).toBeVisible();
	await expect(page.getByText('Cambios guardados')).toBeHidden();
	await expect(monitor.getByRole('button')).toBeDisabled();
	await expect(page.getByRole('button', { name: 'Guardar' })).toBeEnabled();
});
// #endregion
