import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_css_posicional_se_rompe_con_cualquier_cambio_de_layout(page: Page):
    # #region example
    page.locator("body > div:nth-child(2) > div:nth-child(4) > button").click()
    # #endregion
    expect(page.locator("#settings-state")).to_have_text("Cambios guardados")
