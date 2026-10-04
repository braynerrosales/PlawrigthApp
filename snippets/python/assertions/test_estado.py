import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


# #region example
def test_visibilidad_y_estado(page: Page):
    monitor = page.get_by_role("listitem").filter(has_text="Monitor 4K")

    expect(page.get_by_role("heading", name="Productos")).to_be_visible()
    expect(page.get_by_text("Cambios guardados")).to_be_hidden()
    expect(monitor.get_by_role("button")).to_be_disabled()
    expect(page.get_by_role("button", name="Guardar")).to_be_enabled()
# #endregion
