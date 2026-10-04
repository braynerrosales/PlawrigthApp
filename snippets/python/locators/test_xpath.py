import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_xpath_por_contenido_no_por_posicion(page: Page):
    # #region example
    keyboard_price = page.locator("xpath=//li[h3[normalize-space()='Teclado mecánico']]/p")

    expect(keyboard_price).to_have_text("$45")
    # #endregion
