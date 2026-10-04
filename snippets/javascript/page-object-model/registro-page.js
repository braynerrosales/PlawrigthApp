// Page object de la página de práctica `fixtures/acciones.html`. Lo usan los specs del módulo
// y la guía publica la región `example` (id `page-object-model/registro-page`).
// #region example
/**
 * @typedef {object} DatosRegistro
 * @property {string} nombre
 * @property {string} correo
 * @property {string} pais
 * @property {'Gratis' | 'Pro'} plan
 */

export class RegistroPage {
	/** @param {import('@playwright/test').Page} page */
	constructor(page) {
		this.page = page;
		this.nombre = page.getByLabel('Nombre');
		this.correo = page.getByLabel('Correo electrónico');
		this.pais = page.getByLabel('País');
		this.terminos = page.getByLabel('Acepto los términos');
		this.crearCuenta = page.getByRole('button', { name: 'Crear cuenta' });
		this.confirmacion = page.getByRole('status');
	}

	/** @param {DatosRegistro['plan']} nombre */
	plan(nombre) {
		return this.page.getByRole('radio', { name: nombre });
	}

	/** @param {DatosRegistro} datos */
	async completar(datos) {
		await this.nombre.fill(datos.nombre);
		await this.correo.fill(datos.correo);
		await this.pais.selectOption({ label: datos.pais });
		await this.plan(datos.plan).check();
	}

	/** @param {DatosRegistro} datos */
	async registrar(datos) {
		await this.completar(datos);
		await this.terminos.check();
		await this.crearCuenta.click();
	}
}
// #endregion
