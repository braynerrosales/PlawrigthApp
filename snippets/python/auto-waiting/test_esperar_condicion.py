import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


# #region example
def test_esperar_el_resultado_no_el_tiempo(page: Page):
    page.get_by_role("button", name="Cargar pedidos").click()

    expect(page.get_by_role("listitem")).to_have_count(3)
# #endregion
