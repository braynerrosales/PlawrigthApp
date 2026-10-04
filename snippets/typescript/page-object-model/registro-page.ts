// Page object de la página de práctica `fixtures/acciones.html`. Lo usan los specs del módulo
// y la guía publica la región `example` (id `page-object-model/registro-page`).
// #region example
import type { Locator, Page } from '@playwright/test';

export interface DatosRegistro {
	nombre: string;
	correo: string;
	pais: string;
	plan: 'Gratis' | 'Pro';
}

export class RegistroPage {
	readonly page: Page;
	readonly nombre: Locator;
	readonly correo: Locator;
	readonly pais: Locator;
	readonly terminos: Locator;
	readonly crearCuenta: Locator;
	readonly confirmacion: Locator;

	constructor(page: Page) {
		this.page = page;
		this.nombre = page.getByLabel('Nombre');
		this.correo = page.getByLabel('Correo electrónico');
		this.pais = page.getByLabel('País');
		this.terminos = page.getByLabel('Acepto los términos');
		this.crearCuenta = page.getByRole('button', { name: 'Crear cuenta' });
		this.confirmacion = page.getByRole('status');
	}

	plan(nombre: DatosRegistro['plan']): Locator {
		return this.page.getByRole('radio', { name: nombre });
	}

	async completar(datos: DatosRegistro) {
		await this.nombre.fill(datos.nombre);
		await this.correo.fill(datos.correo);
		await this.pais.selectOption({ label: datos.pais });
		await this.plan(datos.plan).check();
	}

	async registrar(datos: DatosRegistro) {
		await this.completar(datos);
		await this.terminos.check();
		await this.crearCuenta.click();
	}
}
// #endregion
