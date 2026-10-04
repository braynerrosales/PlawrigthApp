import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


# #region example
def test_titulo_de_la_pagina(page: Page):
    expect(page).to_have_title("Tienda QA")
# #endregion
