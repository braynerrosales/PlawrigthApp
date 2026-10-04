# Page object de la página de práctica `fixtures/acciones.html`. Lo usan las pruebas del módulo
# y la guía publica la región `example` (id `page-object-model/registro-page`).
# #region example
from dataclasses import dataclass
from typing import Literal

from playwright.sync_api import Locator, Page


@dataclass
class DatosRegistro:
    nombre: str
    correo: str
    pais: str
    plan: Literal["Gratis", "Pro"]


class RegistroPage:
    def __init__(self, page: Page):
        self.page = page
        self.nombre = page.get_by_label("Nombre")
        self.correo = page.get_by_label("Correo electrónico")
        self.pais = page.get_by_label("País")
        self.terminos = page.get_by_label("Acepto los términos")
        self.crear_cuenta = page.get_by_role("button", name="Crear cuenta")
        self.confirmacion = page.get_by_role("status")

    def plan(self, nombre: Literal["Gratis", "Pro"]) -> Locator:
        return self.page.get_by_role("radio", name=nombre)

    def completar(self, datos: DatosRegistro) -> None:
        self.nombre.fill(datos.nombre)
        self.correo.fill(datos.correo)
        self.pais.select_option(label=datos.pais)
        self.plan(datos.plan).check()

    def registrar(self, datos: DatosRegistro) -> None:
        self.completar(datos)
        self.terminos.check()
        self.crear_cuenta.click()
# #endregion
