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
def test_el_login_redirige_a_la_cuenta(page: Page):
    page.goto("/login")
    page.get_by_label("Usuario").fill("ana")
    page.get_by_label("Contraseña").fill("clave-de-prueba")
    page.get_by_role("button", name="Ingresar").click()

    # La redirección ocurre un momento después del clic: to_have_url reintenta hasta que la URL coincide.
    expect(page).to_have_url(re.compile(r"/cuenta$"))
    expect(page.get_by_role("heading", name="Mi cuenta")).to_be_visible()
# #endregion
