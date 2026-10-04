import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_first_last_y_nth_eligen_por_posicion(page: Page):
    # #region example
    add_buttons = page.get_by_role("button", name="Agregar al carrito")
    expect(add_buttons).to_have_count(3)

    add_buttons.first.click()  # índice 0
    add_buttons.nth(1).click()  # índice 1: nth empieza en cero
    expect(add_buttons.last).to_be_disabled()
    # #endregion
    expect(page.get_by_test_id("cart-count")).to_have_text("2")
