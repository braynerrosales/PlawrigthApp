import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("tablas-y-listas"))


def test_contar_una_vez_y_recorrer_con_indices_mientras_la_lista_carga(page: Page):
    movimientos = page.get_by_role("list", name="Últimos movimientos").get_by_role("listitem")

    # #region example
    # count() no espera: la lista todavía está cargando y devuelve 0.
    total = movimientos.count()
    for i in range(total):
        expect(movimientos.nth(i)).to_contain_text("PED-")
    # Fin de la prueba: pasa en verde sin haber comprobado ningún movimiento.
    # #endregion

    # Fuera del ejemplo: demuestra que el bucle no recorrió nada y que la lista sí tenía datos.
    assert total == 0
    expect(movimientos).to_have_count(4)
