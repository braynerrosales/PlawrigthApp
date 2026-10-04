import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_mostrar_un_tooltip(page: Page):
    page.get_by_role("button", name="Más información").hover()

    expect(page.get_by_role("tooltip")).to_have_text("Respondemos en menos de 24 horas")
# #endregion
