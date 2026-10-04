import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("locators"))


def test_css_estable_ids_y_clases_que_el_equipo_controla(page: Page):
    # #region example
    login_form = page.locator("#login-form")
    login_form.get_by_label("Correo electrónico").fill("qa@example.com")

    expect(page.locator("li.product-card")).to_have_count(3)
    # #endregion
    expect(login_form.get_by_label("Correo electrónico")).to_have_value("qa@example.com")
