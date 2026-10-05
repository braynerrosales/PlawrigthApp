import { test } from '@playwright/test';
import assert from 'node:assert/strict';
import { Builder, By, until, type WebDriver } from 'selenium-webdriver';
import { Options } from 'selenium-webdriver/chrome.js';
import { fixtureUrl } from '../support.ts';

// El «antes» de la migración. Playwright Test solo hace de runner para ejecutarlo junto a los demás
// snippets; el código de la prueba es Selenium WebDriver puro (en un proyecto Selenium suele ser Mocha o Jest).

// #region example
let driver: WebDriver;

test.beforeEach(async () => {
	const options = new Options();
	options.addArguments('--headless=new');
	driver = await new Builder().forBrowser('chrome').setChromeOptions(options).build();
});

test.afterEach(async () => {
	await driver.quit();
});

test('exportar pedidos', async () => {
	await driver.get(fixtureUrl('pedidos'));
	await driver.findElement(By.xpath("//button[text()='Cargar pedidos']")).click();

	// Exportar se habilita cuando llegan los datos. Un clic sobre un botón deshabilitado no da error: no hace nada.
	const exportar = await driver.findElement(By.xpath("//button[text()='Exportar']"));
	await driver.wait(until.elementIsEnabled(exportar), 5000);
	await exportar.click();

	const estado = await driver.findElement(By.css("[role='status']"));
	await driver.wait(until.elementTextIs(estado, 'Exportación lista'), 5000);
	assert.equal((await driver.findElements(By.css("ul[aria-label='Pedidos'] li"))).length, 3);
});
// #endregion
