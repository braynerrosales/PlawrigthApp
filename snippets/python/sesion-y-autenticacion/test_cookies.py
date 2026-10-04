import re

from playwright.sync_api import BrowserContext, Page, expect

from support import PORTAL


# #region example
def test_entrar_con_una_cookie_y_perder_la_sesion_al_borrarla(page: Page, context: BrowserContext):
    # Token ficticio de prueba: en un proyecto real lo entrega una API o un login previo.
    context.add_cookies([
        {"name": "sesion", "value": "token-ficticio-ana", "url": PORTAL, "httpOnly": True, "secure": True},
    ])

    page.goto("/panel")
    expect(page.get_by_role("heading", name="Hola, Ana")).to_be_visible()

    context.clear_cookies(name="sesion")
    assert context.cookies(PORTAL) == []

    # Sin cookie, el panel redirige al login.
    page.goto("/panel")
    expect(page).to_have_url(re.compile(r"/login$"))
# #endregion
