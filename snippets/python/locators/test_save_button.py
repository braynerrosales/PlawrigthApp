import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_boton_por_rol_y_nombre_expresa_la_intencion(page: Page):
    # #region example
    page.get_by_role("button", name="Guardar").click()
    # #endregion
    expect(page.get_by_text("Cambios guardados")).to_be_visible()
