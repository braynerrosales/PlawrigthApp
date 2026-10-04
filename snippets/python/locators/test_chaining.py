import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_encadenar_locators_acota_la_busqueda_a_una_region(page: Page):
    # #region example
    product_list = page.get_by_test_id("product-list")
    mouse_card = product_list.get_by_role("listitem").filter(has_text="Mouse inalámbrico")

    mouse_card.get_by_role("button", name="Agregar al carrito").click()

    expect(page.get_by_test_id("cart-count")).to_have_text("1")
    # #endregion
