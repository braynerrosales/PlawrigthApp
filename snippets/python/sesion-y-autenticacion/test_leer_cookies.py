import re

from playwright.sync_api import BrowserContext, Page, expect

from support import PORTAL


# #region example
def test_la_cookie_de_sesion_es_httponly_y_secure(page: Page, context: BrowserContext):
    page.goto("/login")
    page.get_by_label("Usuario").fill("ana")
    page.get_by_label("Contraseña").fill("clave-de-prueba")
    page.get_by_role("button", name="Ingresar").click()
    expect(page).to_have_url(re.compile(r"/panel$"))

    cookies = context.cookies(PORTAL)
    sesion = next(cookie for cookie in cookies if cookie["name"] == "sesion")

    assert sesion["httpOnly"] is True
    assert sesion["secure"] is True
    assert sesion["sameSite"] == "Lax"
    # El JavaScript de la página no puede leer una cookie HttpOnly; la prueba sí, desde el contexto.
    assert "sesion=" not in page.evaluate("() => document.cookie")
# #endregion
