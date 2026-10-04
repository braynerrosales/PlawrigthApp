import { test, expect } from '@playwright/test';

// #region example
test('admin y cliente no comparten sesión', async ({ browser }) => {
	const adminContext = await browser.newContext();
	const clienteContext = await browser.newContext();

	await adminContext.addCookies([
		{ name: 'sesion', value: 'admin-123', url: 'https://tienda-qa.example' },
	]);

	expect(await adminContext.cookies()).toHaveLength(1);
	expect(await clienteContext.cookies()).toHaveLength(0);

	await adminContext.close();
	await clienteContext.close();
});
// #endregion
