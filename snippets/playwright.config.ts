import { defineConfig, devices } from '@playwright/test';
import { API_PRACTICA } from './typescript/support.ts';

/** Ejecuta los snippets publicados en la guía: si un ejemplo deja de funcionar, falla aquí. */
export default defineConfig({
	testDir: '.',
	testMatch: ['typescript/**/*.spec.ts', 'javascript/**/*.spec.js'],
	fullyParallel: true,
	forbidOnly: !!process.env.CI,
	reporter: process.env.CI ? 'github' : 'list',
	use: { actionTimeout: 5_000 },
	projects: [{ name: 'chromium', use: { ...devices['Desktop Chrome'] } }],
	// API de práctica de `server/` (módulo API y Network). El SetUpFixture de C# arranca el mismo script.
	webServer: {
		command: 'node server/api-de-practica.mjs',
		url: `${API_PRACTICA}/api/pedidos`,
		reuseExistingServer: !process.env.CI,
	},
});
