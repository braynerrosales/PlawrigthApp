import pytest
from playwright.sync_api import Error, Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_una_accion_sobre_un_locator_ambiguo_falla_por_strictness(page: Page):
    with pytest.raises(Error, match="strict mode violation"):
        # #region example
        # Hay 3 botones con ese nombre: Playwright se niega a adivinar.
        page.get_by_role("button", name="Agregar al carrito").click()
        # #endregion
