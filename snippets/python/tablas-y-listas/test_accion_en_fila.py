import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


# #region example
def test_cancelar_un_pedido_desde_su_fila(page: Page):
    # La fila se identifica por un dato que el usuario reconoce, no por su posición.
    fila = page.get_by_role("row").filter(has_text="PED-1003")

    # Todas las filas tienen un botón "Cancelar": se busca dentro de la fila.
    fila.get_by_role("button", name="Cancelar").click()

    expect(fila).to_contain_text("Cancelado")
    expect(fila.get_by_role("button", name="Cancelar")).to_be_disabled()
# #endregion
