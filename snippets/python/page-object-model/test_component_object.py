import pytest
from playwright.sync_api import Locator, Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


# #region example
class TarjetaProducto:
    def __init__(self, raiz: Locator):
        self.raiz = raiz
        self.nombre = raiz.get_by_role("heading")
        self.agregar = raiz.get_by_role("button", name="Agregar al carrito")

    def agregar_al_carrito(self) -> None:
        self.agregar.click()


class TiendaPage:
    def __init__(self, page: Page):
        self.page = page
        self.contador_carrito = page.get_by_test_id("cart-count")

    def producto(self, nombre: str) -> TarjetaProducto:
        tarjeta = self.page.get_by_role("listitem").filter(has=self.page.get_by_role("heading", name=nombre))
        return TarjetaProducto(tarjeta)


def test_agregar_un_producto_desde_su_tarjeta(page: Page):
    tienda = TiendaPage(page)

    tienda.producto("Mouse inalámbrico").agregar_al_carrito()

    expect(tienda.contador_carrito).to_have_text("1")
    expect(tienda.producto("Monitor 4K").agregar).to_be_disabled()
# #endregion
