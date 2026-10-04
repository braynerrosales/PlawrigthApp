import pytest
from playwright.sync_api import Page, expect

from registro_page import DatosRegistro, RegistroPage
from support import fixture


# #region example
@pytest.fixture
def registro_page(page: Page) -> RegistroPage:
    # En tu proyecto: page.goto("/registro")
    page.set_content(fixture("acciones"))
    return RegistroPage(page)


def test_registro_de_una_cuenta_gratis(registro_page: RegistroPage):
    registro_page.registrar(DatosRegistro(nombre="Luis Gómez", correo="luis@example.com", pais="Chile", plan="Gratis"))

    expect(registro_page.confirmacion).to_have_text("Cuenta Gratis creada para Luis Gómez")
# #endregion
