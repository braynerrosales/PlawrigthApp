import re

import pytest
from playwright.sync_api import Browser, Page, expect

from support import save_session


# Hace el papel del proyecto setup: inicia sesión una vez por módulo y guarda el estado fuera del repositorio.
@pytest.fixture(scope="module")
def estado_de_ana(browser: Browser, tmp_path_factory):
    ruta = tmp_path_factory.mktemp("portal-qa") / "ana.json"
    save_session(browser, "ana", ruta)
    return ruta


# #region example
@pytest.fixture
def browser_context_args(browser_context_args, estado_de_ana):
    return {**browser_context_args, "storage_state": estado_de_ana}


def test_el_panel_abre_directamente_con_la_sesion_guardada(page: Page):
    # Sin pasar por /login: el contexto ya trae la cookie de sesión.
    page.goto("/panel")

    expect(page).to_have_url(re.compile(r"/panel$"))
    expect(page.get_by_role("heading", name="Hola, Ana")).to_be_visible()


def test_el_cliente_no_ve_la_seccion_de_administracion(page: Page):
    page.goto("/panel")

    expect(page.get_by_text("Rol: cliente")).to_be_visible()
    expect(page.get_by_role("heading", name="Administración")).to_be_hidden()
# #endregion
