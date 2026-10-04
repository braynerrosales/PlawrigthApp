import re

import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


# #region example
def test_buscar_un_pedido_pagina_por_pagina(page: Page):
    pedido = page.get_by_role("row").filter(has_text="PED-1011")
    siguiente = page.get_by_role("button", name="Siguiente")
    indicador = page.get_by_role("navigation", name="Paginación").get_by_role("status")

    # Bucle acotado: si el pedido no aparece en 10 páginas, la prueba falla con un motivo claro.
    pagina = 1
    while not pedido.is_visible():
        if pagina == 10:
            raise AssertionError("PED-1011 no aparece en las primeras 10 páginas")
        siguiente.click()
        # is_visible no espera: antes de mirar otra vez, se espera a que cargue la página nueva.
        expect(indicador).to_have_text(re.compile(rf"^Página {pagina + 1} de"))
        pagina += 1

    expect(pedido).to_contain_text("Karen Sosa")
# #endregion
