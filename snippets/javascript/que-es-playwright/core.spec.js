import { test } from '@playwright/test';

// #region example
import { chromium } from 'playwright';

async function main() {
	const browser = await chromium.launch();
	const context = await browser.newContext();
	const page = await context.newPage();

	await page.goto('https://playwright.dev/');
	console.log(await page.title());

	await browser.close();
}
// #endregion

test('Playwright Core sin test runner', () => main());
