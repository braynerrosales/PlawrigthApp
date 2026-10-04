import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


# #region example
def test_cantidad_y_contenido_de_una_lista(page: Page):
    productos = page.get_by_test_id("product-list").get_by_role("listitem")

    expect(productos).to_have_count(3)
    expect(productos.get_by_role("heading")).to_have_text(["Teclado mecánico", "Mouse inalámbrico", "Monitor 4K"])
# #endregion
