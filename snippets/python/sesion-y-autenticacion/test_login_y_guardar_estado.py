from playwright.sync_api import BrowserContext, Page, expect

from support import PORTAL


# #region example
def test_iniciar_sesion_una_vez_y_guardar_el_estado(page: Page, context: BrowserContext, tmp_path):
    page.goto("/login")
    page.get_by_label("Usuario").fill("ana")
    page.get_by_label("Contraseña").fill("clave-de-prueba")
    page.get_by_role("button", name="Ingresar").click()
    expect(page.get_by_role("heading", name="Hola, Ana")).to_be_visible()

    # Guarda cookies y localStorage en un archivo fuera del repositorio: contiene la sesión.
    estado = context.storage_state(path=tmp_path / "ana.json")

    assert any(cookie["name"] == "sesion" and cookie["httpOnly"] for cookie in estado["cookies"])
    assert {"origin": PORTAL, "localStorage": [{"name": "ultimoUsuario", "value": "ana"}]} in estado["origins"]
# #endregion
