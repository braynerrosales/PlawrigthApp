import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


# #region example
def test_leer_el_estado_de_un_pedido_por_el_nombre_de_la_columna(page: Page):
    tabla = page.get_by_role("table", name="Pedidos")

    # El índice sale del encabezado: si se agrega o se mueve una columna, se recalcula.
    encabezados = tabla.get_by_role("columnheader").all_text_contents()
    columna_estado = encabezados.index("Estado")

    # has + exact: la fila cuya celda es exactamente PED-1003.
    fila = tabla.get_by_role("row").filter(
        has=page.get_by_role("cell", name="PED-1003", exact=True),
    )
    expect(fila.get_by_role("cell").nth(columna_estado)).to_have_text("Pendiente")
# #endregion
