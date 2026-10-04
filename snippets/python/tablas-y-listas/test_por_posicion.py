import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


def test_leer_una_celda_por_su_posicion(page: Page):
    # #region example
    # "Fila 3, columna 2": hoy es el cliente de PED-1003.
    cliente = page.locator("tbody tr:nth-child(3) td:nth-child(2)")
    expect(cliente).to_have_text("Elena Mora")
    # #endregion

    # Fuera del ejemplo: al ordenar por cliente, la misma posición es otro pedido.
    page.get_by_role("button", name="Cliente").click()
    expect(cliente).to_have_text("Carla Gómez")
