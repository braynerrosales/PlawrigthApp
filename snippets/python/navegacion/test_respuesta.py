import pytest
from playwright.sync_api import BrowserContext, Page, expect

from support import TIENDA, serve_tienda


@pytest.fixture(autouse=True)
def tienda(context: BrowserContext):
    serve_tienda(context)


@pytest.fixture
def browser_context_args(browser_context_args):
    return {**browser_context_args, "base_url": TIENDA}


# #region example
def test_una_ruta_inexistente_responde_404_sin_lanzar_excepcion(page: Page):
    # goto devuelve la respuesta del documento principal y no falla por un 404 o un 500.
    response = page.goto("/no-existe")

    assert response is not None
    assert response.status == 404
    assert response.ok is False
    expect(page.get_by_role("heading", name="Página no encontrada")).to_be_visible()
# #endregion
