import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_xpath_absoluto_copiado_de_devtools(page: Page):
    # #region example
    page.locator("xpath=/html/body/div/div[4]/button").click()
    # #endregion
    expect(page.locator("#settings-state")).to_have_text("Cambios guardados")
