import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_registro_de_una_cuenta_pro(page: Page):
    page.get_by_label("Nombre").fill("Ana Pérez")
    page.get_by_label("Correo electrónico").fill("ana@example.com")
    page.get_by_label("País").select_option(label="Colombia")
    page.get_by_role("radio", name="Pro").check()
    page.get_by_label("Acepto los términos").check()

    page.get_by_role("button", name="Crear cuenta").click()

    expect(page.get_by_role("status")).to_have_text("Cuenta Pro creada para Ana Pérez")
# #endregion
