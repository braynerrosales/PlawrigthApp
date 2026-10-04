import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


def test_leer_el_texto_una_vez_falla_con_datos_que_llegan_tarde(page: Page):
    with pytest.raises(AssertionError):
        # #region example
        page.get_by_role("button", name="Cargar pedidos").click()
        texto = page.get_by_role("status").text_content()
        assert texto == "3 pedidos"  # falla: leyó «Cargando…» y no vuelve a intentarlo
        # #endregion
