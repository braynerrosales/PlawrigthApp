import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


# #region example
def test_espera_fija_antes_de_actuar(page: Page):
    page.wait_for_timeout(1000)  # "por si el aviso no se fue"
    page.get_by_role("button", name="Cargar pedidos").click()
    page.wait_for_timeout(2000)  # "por si los datos tardan"

    expect(page.get_by_role("listitem")).to_have_count(3)
# #endregion
