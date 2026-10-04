import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


# #region example
def test_negacion_y_timeout_propio(page: Page):
    page.get_by_role("button", name="Cargar pedidos").click()

    expect(page.get_by_text("Cargando…")).not_to_be_visible()
    expect(page.get_by_role("button", name="Exportar")).to_be_enabled(timeout=10_000)
# #endregion
