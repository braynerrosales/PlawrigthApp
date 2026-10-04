import re

import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


# #region example
def test_ordenar_por_cliente_y_comprobar_el_orden(page: Page):
    tabla = page.get_by_role("table", name="Pedidos")
    # Solo las filas con celdas de datos: la fila del encabezado tiene columnheader, no cell.
    filas = tabla.get_by_role("row").filter(has=page.get_by_role("cell"))

    tabla.get_by_role("button", name="Cliente").click()

    expect(tabla.get_by_role("columnheader", name="Cliente")).to_have_attribute("aria-sort", "ascending")
    # Una lista comprueba la cantidad de filas y el texto de cada una, en orden.
    expect(filas).to_have_text([
        re.compile("Ana Torres"),
        re.compile("Bruno Ríos"),
        re.compile("Carla Gómez"),
        re.compile("Diego Ruiz"),
        re.compile("Elena Mora"),
    ])
# #endregion
