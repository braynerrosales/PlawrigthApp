import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_completar_y_limpiar_campos(page: Page):
    nombre = page.get_by_label("Nombre")

    nombre.fill("Ana Pérez")
    expect(nombre).to_have_value("Ana Pérez")

    nombre.clear()
    expect(nombre).to_have_value("")
# #endregion
