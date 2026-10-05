import { test, expect } from '@playwright/test';
import { fixture } from '../support.ts';

// #region example
test('exportar la página a PDF', async ({ page, browserName }) => {
	test.skip(browserName !== 'chromium', 'page.pdf() solo funciona en Chromium'); // [!mark]

	await page.setContent(fixture('pedidos'));
	const pdf = await page.pdf();

	expect(pdf.subarray(0, 4).toString()).toBe('%PDF');
});
// #endregion
