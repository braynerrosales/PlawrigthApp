import re

import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


# #region example
def test_filtrar_y_comprobar_que_solo_quedan_los_pedidos_esperados(page: Page):
    filas = page.get_by_role("table", name="Pedidos").get_by_role("row").filter(has=page.get_by_role("cell"))

    page.get_by_label("Filtrar pedidos").fill("Pendiente")

    # La app aplica el filtro 300 ms después: las aserciones reintentan hasta verlo.
    expect(filas).to_have_count(3)
    expect(filas).to_have_text([re.compile("PED-1003"), re.compile("PED-1006"), re.compile("PED-1009")])
# #endregion
