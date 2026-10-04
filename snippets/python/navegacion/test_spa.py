import re

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
def test_navegar_dentro_de_una_spa(page: Page):
    page.goto("/cuenta")
    expect(page.get_by_role("heading", name="Perfil")).to_be_visible()

    # La app cambia la URL con history.pushState: no se carga un documento nuevo.
    page.get_by_role("link", name="Pedidos").click()
    expect(page).to_have_url(re.compile(r"/cuenta/pedidos$"))
    expect(page.get_by_role("heading", name="Pedidos")).to_be_visible()

    # Atrás también funciona: la app escucha popstate y vuelve a mostrar Perfil.
    page.go_back()
    expect(page).to_have_url(re.compile(r"/cuenta$"))
    expect(page.get_by_role("heading", name="Perfil")).to_be_visible()
# #endregion
