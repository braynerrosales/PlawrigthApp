import pytest
from playwright.sync_api import Page, expect


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(
        """<button onclick="window.open('').document.write('<h1>Ayuda</h1>')">Abrir ayuda</button>""",
    )


# #region example
def test_la_ayuda_se_abre_en_una_ventana_nueva(page: Page):
    with page.expect_popup() as popup_info:
        page.get_by_role("button", name="Abrir ayuda").click()
    popup = popup_info.value

    expect(popup.get_by_role("heading", name="Ayuda")).to_be_visible()

    popup.close()
    expect(page.get_by_role("button", name="Abrir ayuda")).to_be_visible()
# #endregion
