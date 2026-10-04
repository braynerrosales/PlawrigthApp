import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


def test_una_asercion_que_falla_explica_que_esperaba_y_que_encontro(page: Page):
    with pytest.raises(AssertionError, match="to_have_count"):
        # #region example
        # Falta un paso: nadie hizo clic en "Cargar pedidos", así que la lista sigue vacía.
        expect(page.get_by_role("list", name="Pedidos").get_by_role("listitem")).to_have_count(3, timeout=2000)
        # #endregion
