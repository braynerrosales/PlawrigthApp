import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_renombrar_con_doble_clic(page: Page):
    page.get_by_text("Mi lista").dblclick()

    nombre = page.get_by_role("textbox", name="Nombre de la lista")
    expect(nombre).to_be_focused()
    nombre.fill("Pruebas de regresión")
    expect(nombre).to_have_value("Pruebas de regresión")
# #endregion
