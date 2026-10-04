import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("formularios"))


# #region example
def test_enviar_con_enter_y_comprobar_el_resultado(page: Page):
    correo = page.get_by_label("Correo electrónico")
    fecha = page.get_by_label("Fecha de entrega")

    fecha.fill("2026-10-04")
    correo.fill("ana@example.com")
    # Enter dentro de un campo envía el formulario, como lo haría una persona.
    correo.press("Enter")

    # El resultado visible y el estado posterior del formulario.
    expect(page.get_by_role("status")).to_have_text("Solicitud PED-1042 enviada")
    expect(page.get_by_role("alert")).to_have_count(0)
    expect(correo).to_be_empty()
    expect(fecha).to_be_empty()
# #endregion
