import { existsSync } from 'node:fs';
import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test('graba un trace de un flujo con la Tracing API', async ({ page, context }) => {
	const tracePath = test.info().outputPath('pedidos-trace.zip');
	// #region example
	await context.tracing.start({ screenshots: true, snapshots: true, sources: true });
	try {
		await page.setContent(fixture('pedidos'));
		await page.getByRole('button', { name: 'Cargar pedidos' }).click();
		await expect(page.getByRole('status')).toHaveText('3 pedidos');
	} finally {
		// Sin stop no hay archivo: el trace queda en memoria y se pierde.
		await context.tracing.stop({ path: tracePath });
	}
	// #endregion
	expect(existsSync(tracePath)).toBe(true);
});
