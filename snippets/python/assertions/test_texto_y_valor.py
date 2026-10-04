import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


# #region example
def test_texto_valor_y_atributos(page: Page):
    expect(page.get_by_test_id("cart-count")).to_have_text("0")
    expect(page.get_by_text("Envío gratis")).to_contain_text("mayores a $50")

    buscador = page.get_by_placeholder("Buscar productos")
    expect(buscador).to_have_attribute("type", "search")
    buscador.fill("teclado")
    expect(buscador).to_have_value("teclado")
# #endregion
