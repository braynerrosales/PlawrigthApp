import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


# #region example
def test_el_cliente_agrega_un_producto_disponible_al_carrito(page: Page):
    page.get_by_label("Correo electrónico").fill("qa@example.com")
    page.get_by_label("Contraseña").fill("clave-de-prueba")
    page.get_by_role("button", name="Iniciar sesión").click()
    expect(page.get_by_role("status")).to_have_text("Bienvenido, qa@example.com")

    product = page.get_by_role("listitem").filter(has_text="Mouse inalámbrico")  # [!mark]
    product.get_by_role("button", name="Agregar al carrito").click()  # [!mark]

    expect(page.get_by_test_id("cart-count")).to_have_text("1")
# #endregion
