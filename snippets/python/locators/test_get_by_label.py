import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_get_by_label_localiza_campos_por_su_etiqueta(page: Page):
    # #region example
    page.get_by_label("Correo electrónico").fill("qa@example.com")
    page.get_by_label("Contraseña").fill("clave-de-prueba")

    expect(page.get_by_label("Contraseña")).to_have_value("clave-de-prueba")
    # #endregion
