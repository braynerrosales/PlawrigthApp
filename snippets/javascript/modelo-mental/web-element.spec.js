import { test } from '@playwright/test';
import assert from 'node:assert/strict';
import { Builder, By, error, until } from 'selenium-webdriver';
import { Options } from 'selenium-webdriver/chrome.js';
import { fixtureUrl } from '../support.js';

// Selenium WebDriver puro; Playwright Test solo hace de runner (ver migracion/selenium.spec.js).
/** @type {import('selenium-webdriver').WebDriver} */
let driver;

test.beforeEach(async () => {
	const options = new Options();
	options.addArguments('--headless=new');
	driver = await new Builder().forBrowser('chrome').setChromeOptions(options).build();
	await driver.get(fixtureUrl('pedidos'));
});

test.afterEach(async () => {
	await driver.quit();
});

// #region example
test('un WebElement queda obsoleto cuando la lista se vuelve a dibujar', async () => {
	const cargar = await driver.findElement(By.xpath("//button[text()='Cargar pedidos']"));
	const estado = await driver.findElement(By.css("[role='status']"));
	await cargar.click();
	await driver.wait(until.elementTextIs(estado, '3 pedidos'), 5000);

	const primero = await driver.findElement(By.css("ul[aria-label='Pedidos'] li"));
	assert.equal(await primero.getText(), 'PED-1001 · Pagado');

	// La página reemplaza la lista: el elemento que se guardó ya no existe en el DOM.
	await cargar.click();
	await driver.wait(until.elementTextIs(estado, '3 pedidos'), 5000);
	await assert.rejects(primero.getText(), error.StaleElementReferenceError); // [!mark]
});
// #endregion
