import re

import pytest
from playwright.sync_api import Page, expect


# #region example
@pytest.fixture(autouse=True)
def iniciar_sesion(page: Page):
    # Cada prueba repite el formulario de login antes de hacer lo que de verdad quiere probar.
    page.goto("/login")
    page.get_by_label("Usuario").fill("ana")
    page.get_by_label("Contraseña").fill("clave-de-prueba")
    page.get_by_role("button", name="Ingresar").click()
    expect(page).to_have_url(re.compile(r"/panel$"))


def test_ver_el_saludo(page: Page):
    expect(page.get_by_role("heading", name="Hola, Ana")).to_be_visible()


def test_ver_el_rol(page: Page):
    expect(page.get_by_text("Rol: cliente")).to_be_visible()
# #endregion
