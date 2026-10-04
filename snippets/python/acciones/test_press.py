import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_buscar_con_enter(page: Page):
    buscador = page.get_by_role("searchbox", name="Buscar en la ayuda")

    buscador.fill("facturas")
    buscador.press("Enter")

    expect(page.get_by_text("Resultados para «facturas»")).to_be_visible()
# #endregion
