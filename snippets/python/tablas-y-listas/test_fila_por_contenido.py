import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


# #region example
def test_la_fila_por_su_contenido_sigue_siendo_la_misma_al_ordenar(page: Page):
    pedido = page.get_by_role("row").filter(has_text="PED-1003")
    expect(pedido).to_contain_text("Elena Mora")

    # La tabla se ordena y se vuelve a dibujar: la fila cambia de posición.
    page.get_by_role("button", name="Cliente").click()

    # El locator se resuelve otra vez y encuentra la misma fila de datos.
    expect(pedido).to_contain_text("Elena Mora")
# #endregion
