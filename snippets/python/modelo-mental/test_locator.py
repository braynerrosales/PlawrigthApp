import pytest
from playwright.sync_api import Page, expect

from support import fixture_url


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.goto(fixture_url("pedidos"))


# #region example
def test_locator_se_vuelve_a_buscar(page: Page):
    cargar = page.get_by_role("button", name="Cargar pedidos")
    estado = page.get_by_role("status")
    cargar.click()
    expect(estado).to_have_text("3 pedidos")

    primero = page.get_by_role("listitem").first
    expect(primero).to_have_text("PED-1001 · Pagado")

    # La página reemplaza la lista: el Locator encuentra el elemento nuevo.
    cargar.click()
    expect(estado).to_have_text("3 pedidos")
    expect(primero).to_have_text("PED-1001 · Pagado")  # [!mark]
# #endregion
