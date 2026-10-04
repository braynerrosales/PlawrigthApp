import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_elegir_una_opcion_por_su_texto(page: Page):
    pais = page.get_by_label("País")

    pais.select_option(label="Chile")

    expect(pais).to_have_value("cl")
# #endregion
