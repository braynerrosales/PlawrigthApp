import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("iframes-y-dialogs"))


# #region example
def test_el_pago_se_completa_dentro_del_iframe(page: Page):
    pago = page.frame_locator('iframe[title="Pago con tarjeta"]')

    pago.get_by_label("Número de tarjeta").fill("4111 1111 1111 1111")
    pago.get_by_role("button", name="Pagar").click()

    expect(pago.get_by_role("status")).to_have_text("Pago aprobado")
# #endregion
