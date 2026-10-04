import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('tablas-y-listas'));
});

// #region example
test('buscar un pedido página por página', async ({ page }) => {
	const pedido = page.getByRole('row').filter({ hasText: 'PED-1011' });
	const siguiente = page.getByRole('button', { name: 'Siguiente' });
	const indicador = page.getByRole('navigation', { name: 'Paginación' }).getByRole('status');

	// Bucle acotado: si el pedido no aparece en 10 páginas, la prueba falla con un motivo claro.
	for (let pagina = 1; !(await pedido.isVisible()); pagina++) {
		if (pagina === 10) throw new Error('PED-1011 no aparece en las primeras 10 páginas');
		await siguiente.click();
		// isVisible no espera: antes de mirar otra vez, se espera a que cargue la página nueva.
		await expect(indicador).toHaveText(new RegExp(`^Página ${pagina + 1} de`));
	}

	await expect(pedido).toContainText('Karen Sosa');
});
// #endregion
