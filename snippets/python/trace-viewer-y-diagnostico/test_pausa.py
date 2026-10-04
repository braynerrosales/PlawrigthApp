from playwright.sync_api import Page, expect

from support import fixture


def test_marca_donde_detenerse_al_depurar(page: Page):
    page.set_content(fixture("pedidos"))
    # #region example
    page.get_by_role("button", name="Cargar pedidos").click()

    # Descomenta solo mientras depuras y ejecuta en modo headed (--headed con PWDEBUG=1).
    # Nunca lo subas al repositorio: en CI la prueba quedaría detenida hasta el timeout.
    # page.pause()

    expect(page.get_by_role("status")).to_have_text("3 pedidos")
    # #endregion
