import { test, expect } from '@playwright/test';
import { fixture } from '../support.js';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('locators'));
});

// #region example
class TarjetaProducto {
	/** @param {import('@playwright/test').Locator} raiz */
	constructor(raiz) {
		this.raiz = raiz;
		this.nombre = raiz.getByRole('heading');
		this.agregar = raiz.getByRole('button', { name: 'Agregar al carrito' });
	}

	async agregarAlCarrito() {
		await this.agregar.click();
	}
}

class TiendaPage {
	/** @param {import('@playwright/test').Page} page */
	constructor(page) {
		this.page = page;
		this.contadorCarrito = page.getByTestId('cart-count');
	}

	/** @param {string} nombre */
	producto(nombre) {
		const tarjeta = this.page.getByRole('listitem').filter({ has: this.page.getByRole('heading', { name: nombre }) });
		return new TarjetaProducto(tarjeta);
	}
}

test('agregar un producto desde su tarjeta', async ({ page }) => {
	const tienda = new TiendaPage(page);

	await tienda.producto('Mouse inalámbrico').agregarAlCarrito();

	await expect(tienda.contadorCarrito).toHaveText('1');
	await expect(tienda.producto('Monitor 4K').agregar).toBeDisabled();
});
// #endregion
