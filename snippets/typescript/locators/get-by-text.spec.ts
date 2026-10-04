import { test, expect } from '@playwright/test';
import { locatorsHtml } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(locatorsHtml);
});

test('getByText localiza contenido visible sin rol interactivo', async ({ page }) => {
	// #region example
	await expect(page.getByText('Envío gratis en pedidos mayores a $50')).toBeVisible();

	// Por defecto: subcadena, sin distinguir mayúsculas, espacios normalizados.
	await expect(page.getByText('envío gratis')).toBeVisible();

	// exact: true exige el texto completo y respeta mayúsculas.
	await expect(page.getByText('Agotado', { exact: true })).toBeVisible();
	// #endregion
});
