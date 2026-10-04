import { test, expect, type Locator, type Page } from '@playwright/test';
import { fixture } from '../support.ts';

test.beforeEach(async ({ page }) => {
	await page.setContent(fixture('locators'));
});

// #region example
class TarjetaProducto {
	readonly raiz: Locator;
	readonly nombre: Locator;
	readonly agregar: Locator;

	constructor(raiz: Locator) {
		this.raiz = raiz;
		this.nombre = raiz.getByRole('heading');
		this.agregar = raiz.getByRole('button', { name: 'Agregar al carrito' });
	}

	async agregarAlCarrito() {
		await this.agregar.click();
	}
}

class TiendaPage {
	readonly page: Page;
	readonly contadorCarrito: Locator;

	constructor(page: Page) {
		this.page = page;
		this.contadorCarrito = page.getByTestId('cart-count');
	}

	producto(nombre: string) {
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
