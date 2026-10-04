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
def test_volver_atras_y_adelante_en_el_historial(page: Page):
    page.goto("/")
    page.get_by_role("link", name="Productos").click()
    expect(page).to_have_url(re.compile(r"/productos$"))

    page.go_back()
    expect(page).to_have_url("https://tienda.test/")
    expect(page.get_by_role("heading", name="Bienvenido a Tienda QA")).to_be_visible()

    page.go_forward()
    expect(page).to_have_url(re.compile(r"/productos$"))
    expect(page.get_by_role("heading", name="Productos")).to_be_visible()
# #endregion
