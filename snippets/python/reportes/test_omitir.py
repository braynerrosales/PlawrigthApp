import pytest
from playwright.sync_api import Page

from support import fixture


# #region example
@pytest.mark.only_browser("chromium")  # [!mark]
def test_exportar_la_pagina_a_pdf(page: Page):
    page.set_content(fixture("pedidos"))
    pdf = page.pdf()

    assert pdf[:4] == b"%PDF"
# #endregion
