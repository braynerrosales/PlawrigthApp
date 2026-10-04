import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_get_by_role_localiza_por_rol_y_nombre_accesible(page: Page):
    # #region example
    page.get_by_role("textbox", name="Correo electrónico").fill("qa@example.com")
    page.get_by_role("checkbox", name="Recordarme").check()
    page.get_by_role("button", name="Iniciar sesión").click()

    expect(page.get_by_role("heading", name="Productos", level=2)).to_be_visible()
    # #endregion
    expect(page.get_by_role("status")).to_have_text("Bienvenido, qa@example.com")
