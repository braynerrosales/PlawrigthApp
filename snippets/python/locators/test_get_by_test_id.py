import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_get_by_test_id_localiza_por_un_atributo_de_pruebas(page: Page):
    # #region example
    expect(page.get_by_test_id("cart-count")).to_have_text("0")
    expect(page.get_by_test_id("product-list").get_by_role("listitem")).to_have_count(3)
    # #endregion
