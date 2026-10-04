import pytest
from playwright.sync_api import BrowserContext, Page, expect

from support import serve_tienda


@pytest.fixture(autouse=True)
def tienda(context: BrowserContext):
    serve_tienda(context)


# #region example
@pytest.fixture
def browser_context_args(browser_context_args):
    return {**browser_context_args, "base_url": "https://tienda.test"}


def test_abrir_la_pagina_de_inicio(page: Page):
    # La ruta es relativa a base_url: https://tienda.test/
    page.goto("/")

    expect(page.get_by_role("heading", name="Bienvenido a Tienda QA")).to_be_visible()
# #endregion
