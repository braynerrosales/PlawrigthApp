import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_un_locator_se_resuelve_de_nuevo_en_cada_uso(page: Page):
    # #region example
    # Describe CÓMO encontrar el elemento; todavía no busca nada en la página.
    status = page.get_by_role("status")
    expect(status).to_be_empty()

    page.get_by_label("Correo electrónico").fill("qa@example.com")
    page.get_by_role("button", name="Iniciar sesión").click()

    # La app reemplazó el nodo; el mismo Locator encuentra el nuevo.
    expect(status).to_have_text("Bienvenido, qa@example.com")
    # #endregion
