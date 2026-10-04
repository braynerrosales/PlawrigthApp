import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_get_by_text_localiza_contenido_visible_sin_rol_interactivo(page: Page):
    # #region example
    expect(page.get_by_text("Envío gratis en pedidos mayores a $50")).to_be_visible()

    # Por defecto: subcadena, sin distinguir mayúsculas, espacios normalizados.
    expect(page.get_by_text("envío gratis")).to_be_visible()

    # exact=True exige el texto completo y respeta mayúsculas.
    expect(page.get_by_text("Agotado", exact=True)).to_be_visible()
    # #endregion
