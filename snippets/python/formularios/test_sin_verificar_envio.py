import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("formularios"))


def test_enviar_el_formulario_sin_comprobar_el_resultado(page: Page):
    # #region example
    page.get_by_label("Correo electrónico").fill("ana@correo")
    page.get_by_label("Fecha de entrega").fill("2026-10-04")
    page.get_by_role("button", name="Enviar solicitud").click()
    # Fin de la prueba: pasa en verde, aunque la app rechazó el correo.
    # #endregion

    # Fuera del ejemplo: demuestra que el envío en realidad falló.
    expect(page.get_by_role("alert")).to_have_text("Ingresa un correo válido")
