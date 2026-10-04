import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_asignar_el_valor_con_javascript_no_dispara_eventos(page: Page):
    page.get_by_label("Nombre").evaluate("el => el.value = 'Ana Pérez'")
    page.get_by_label("Correo electrónico").evaluate("el => el.value = 'ana@example.com'")
    page.get_by_label("Acepto los términos").check()

    # La app nunca recibió eventos input en los campos de texto: el botón sigue deshabilitado.
    expect(page.get_by_role("button", name="Crear cuenta")).to_be_disabled()
# #endregion
