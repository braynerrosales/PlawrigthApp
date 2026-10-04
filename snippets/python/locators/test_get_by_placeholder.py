import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_get_by_placeholder_localiza_un_campo_sin_etiqueta(page: Page):
    # #region example
    search = page.get_by_placeholder("Buscar productos")
    search.fill("teclado")

    expect(search).to_have_value("teclado")
    # #endregion
