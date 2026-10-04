import pytest
from playwright.sync_api import Browser, expect

from support import PORTAL, save_session, serve_portal


@pytest.fixture(scope="module")
def estados(browser: Browser, tmp_path_factory):
    # Fuera del repositorio: los archivos contienen las sesiones.
    carpeta = tmp_path_factory.mktemp("portal-qa-roles")
    rutas = {usuario: carpeta / f"{usuario}.json" for usuario in ("admin", "ana")}
    for usuario, ruta in rutas.items():
        save_session(browser, usuario, ruta)
    return rutas


@pytest.fixture
def estado_de_admin(estados):
    return estados["admin"]


@pytest.fixture
def estado_de_ana(estados):
    return estados["ana"]


# #region example
def test_admin_y_cliente_en_la_misma_prueba(browser: Browser, estado_de_admin, estado_de_ana):
    admin_context = browser.new_context(base_url=PORTAL, storage_state=estado_de_admin)
    cliente_context = browser.new_context(base_url=PORTAL, storage_state=estado_de_ana)
    serve_portal(admin_context)
    serve_portal(cliente_context)

    admin_page = admin_context.new_page()
    cliente_page = cliente_context.new_page()
    admin_page.goto("/panel")
    cliente_page.goto("/panel")

    expect(admin_page.get_by_role("heading", name="Administración")).to_be_visible()
    expect(cliente_page.get_by_role("heading", name="Hola, Ana")).to_be_visible()
    expect(cliente_page.get_by_role("heading", name="Administración")).to_be_hidden()

    admin_context.close()
    cliente_context.close()
# #endregion
