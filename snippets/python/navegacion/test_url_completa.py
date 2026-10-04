import pytest
from playwright.sync_api import BrowserContext, Page, expect

from support import serve_tienda


@pytest.fixture(autouse=True)
def tienda(context: BrowserContext):
    serve_tienda(context)


# #region example
def test_ver_el_catalogo(page: Page):
    page.goto("https://tienda.test/productos")
    expect(page.get_by_role("heading", name="Productos")).to_be_visible()


def test_abrir_el_inicio_de_sesion(page: Page):
    # La misma URL completa, repetida en cada prueba: cambiar de ambiente obliga a editarlas todas.
    page.goto("https://tienda.test/login")
    expect(page.get_by_role("heading", name="Iniciar sesión")).to_be_visible()
# #endregion
