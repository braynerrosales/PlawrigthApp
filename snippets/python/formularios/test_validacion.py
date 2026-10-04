import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("formularios"))


# #region example
def test_un_correo_invalido_muestra_el_error_de_la_app(page: Page):
    correo = page.get_by_label("Correo electrónico")

    correo.fill("ana@correo")
    page.get_by_label("Fecha de entrega").fill("2026-10-04")
    page.get_by_role("button", name="Enviar solicitud").click()

    # El mensaje es de la app, no del navegador: es estable y se puede comprobar.
    expect(page.get_by_role("alert")).to_have_text("Ingresa un correo válido")
    expect(correo).to_have_attribute("aria-invalid", "true")
    expect(page.get_by_role("status")).to_be_empty()
# #endregion
