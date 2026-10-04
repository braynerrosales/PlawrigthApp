import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("iframes-y-dialogs"))


# #region example
def test_los_terminos_se_leen_en_otra_pestana_y_se_aceptan_en_la_original(page: Page):
    with page.expect_popup() as terminos_info:
        page.get_by_role("button", name="Ver términos y condiciones").click()
    terminos = terminos_info.value

    # Las dos pestañas están abiertas y se usan a la vez, sin «cambiar» a ninguna.
    expect(terminos).to_have_title("Términos y condiciones")
    expect(terminos.get_by_text("Versión vigente: octubre de 2026")).to_be_visible()
    page.get_by_label("Acepto los términos y condiciones").check()
    assert len(page.context.pages) == 2

    terminos.close()
    assert len(page.context.pages) == 1
    expect(page.get_by_label("Acepto los términos y condiciones")).to_be_checked()
# #endregion
