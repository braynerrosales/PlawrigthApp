import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


# #region example
def test_esperar_una_lista_que_se_carga_con_retraso(page: Page):
    movimientos = page.get_by_role("list", name="Últimos movimientos").get_by_role("listitem")

    # Reintenta hasta que la lista tiene exactamente estos elementos, en este orden.
    expect(movimientos).to_have_text(["PED-1003 pagado", "PED-1007 enviado", "PED-1001 entregado", "PED-1005 cancelado"])

    # Leer los textos solo cuando necesitas los datos, y después de la aserción: all_text_contents no espera.
    textos = movimientos.all_text_contents()
    assert [texto for texto in textos if texto.endswith("cancelado")] == ["PED-1005 cancelado"]
# #endregion
