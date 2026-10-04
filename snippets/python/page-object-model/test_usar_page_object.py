import pytest
from playwright.sync_api import Page, expect

from registro_page import DatosRegistro, RegistroPage
from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
ana = DatosRegistro(nombre="Ana Pérez", correo="ana@example.com", pais="Colombia", plan="Pro")


def test_registro_de_una_cuenta_pro(page: Page):
    registro = RegistroPage(page)

    registro.registrar(ana)

    expect(registro.confirmacion).to_have_text("Cuenta Pro creada para Ana Pérez")


def test_sin_aceptar_los_terminos_no_se_puede_crear_la_cuenta(page: Page):
    registro = RegistroPage(page)

    registro.completar(ana)

    expect(registro.crear_cuenta).to_be_disabled()
# #endregion
