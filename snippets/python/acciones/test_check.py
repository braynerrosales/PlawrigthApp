import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_marcar_checkboxes_y_radios(page: Page):
    page.get_by_label("Acepto los términos").check()
    page.get_by_label("Recibir novedades").uncheck()
    page.get_by_role("radio", name="Pro").check()

    expect(page.get_by_label("Acepto los términos")).to_be_checked()
    expect(page.get_by_label("Recibir novedades")).not_to_be_checked()
    expect(page.get_by_role("radio", name="Gratis")).not_to_be_checked()
# #endregion
