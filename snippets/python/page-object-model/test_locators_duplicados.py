import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_registro_de_una_cuenta_gratis(page: Page):
    page.get_by_label("Nombre").fill("Ana Pérez")
    page.get_by_label("Correo electrónico").fill("ana@example.com")
    page.get_by_label("Acepto los términos").check()
    page.get_by_role("button", name="Crear cuenta").click()

    expect(page.get_by_role("status")).to_have_text("Cuenta Gratis creada para Ana Pérez")


def test_sin_aceptar_los_terminos_no_se_puede_crear_la_cuenta(page: Page):
    page.get_by_label("Nombre").fill("Ana Pérez")
    page.get_by_label("Correo electrónico").fill("ana@example.com")

    expect(page.get_by_role("button", name="Crear cuenta")).to_be_disabled()
# #endregion
