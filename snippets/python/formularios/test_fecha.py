import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("formularios"))


# #region example
def test_completar_un_campo_de_fecha(page: Page):
    fecha = page.get_by_label("Fecha de entrega")

    # Siempre en formato ISO (yyyy-mm-dd), sin importar cómo lo muestre el navegador.
    fecha.fill("2026-10-04")

    expect(fecha).to_have_value("2026-10-04")
    expect(page.get_by_text("Entrega: 04/10/2026")).to_be_visible()
# #endregion
