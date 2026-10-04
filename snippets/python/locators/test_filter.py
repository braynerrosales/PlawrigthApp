import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_filter_acota_una_lista_por_texto_o_por_contenido(page: Page):
    # #region example
    keyboard = page.get_by_role("listitem").filter(has_text="Teclado mecánico")
    keyboard.get_by_role("button", name="Agregar al carrito").click()
    expect(page.get_by_test_id("cart-count")).to_have_text("1")

    # has: el item debe contener otro Locator.
    sold_out = page.get_by_role("listitem").filter(
        has=page.get_by_text("Agotado", exact=True),
    )
    expect(sold_out.get_by_role("heading")).to_have_text("Monitor 4K")
    # #endregion
