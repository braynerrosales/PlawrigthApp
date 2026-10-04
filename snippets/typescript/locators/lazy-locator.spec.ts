import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('un Locator se resuelve de nuevo en cada uso', async ({ page }) => {
	// #region example
	// Describe CÓMO encontrar el elemento; todavía no busca nada en la página.
	const status = page.getByRole('status');
	await expect(status).toBeEmpty();

	await page.getByLabel('Correo electrónico').fill('qa@example.com');
	await page.getByRole('button', { name: 'Iniciar sesión' }).click();

	// La app reemplazó el nodo; el mismo Locator encuentra el nuevo.
	await expect(status).toHaveText('Bienvenido, qa@example.com');
	// #endregion
});
