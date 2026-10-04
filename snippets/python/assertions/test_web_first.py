import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


# #region example
def test_la_asercion_espera_a_que_lleguen_los_datos(page: Page):
    page.get_by_role("button", name="Cargar pedidos").click()

    expect(page.get_by_role("status")).to_have_text("3 pedidos")
# #endregion
