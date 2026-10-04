from playwright.sync_api import Page, expect

from support import fixture


def test_depurar_subiendo_esperas_y_timeouts(page: Page):
    page.set_content(fixture("pedidos"))
    # #region example
    # "Falló una vez en CI": se agrega una espera, se sube el timeout y se imprime el HTML.
    page.wait_for_timeout(1000)
    page.get_by_role("button", name="Cargar pedidos").click(timeout=60_000)
    print(page.get_by_role("status").text_content())
    expect(page.get_by_role("status")).to_have_text("3 pedidos", timeout=60_000)
    # #endregion
