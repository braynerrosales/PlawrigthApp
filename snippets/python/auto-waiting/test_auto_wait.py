import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


# #region example
def test_exportar_cuando_los_datos_estan_listos(page: Page):
    # Al empezar, un aviso tapa la página y Exportar está deshabilitado hasta que llegan los datos.
    page.get_by_role("button", name="Cargar pedidos").click()
    page.get_by_role("button", name="Exportar").click()

    expect(page.get_by_role("status")).to_have_text("Exportación lista")
# #endregion
