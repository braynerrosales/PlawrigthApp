import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


# #region example
@pytest.mark.smoke  # [!mark]
def test_exportar_pedidos(page: Page):
    page.get_by_role("button", name="Cargar pedidos").click()
    page.get_by_role("button", name="Exportar").click()

    expect(page.get_by_role("status")).to_have_text("Exportación lista")
# #endregion
