import pytest
from playwright.sync_api import Page, expect

from support import fixture

intentos = 0


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


# #region example
@pytest.mark.flaky(reruns=2)  # [!mark]
def test_exportar_pedidos(page: Page):
    # Simula un fallo intermitente: solo falla el primer intento.
    global intentos
    intentos += 1
    assert intentos > 1, "fallo intermitente simulado"  # [!mark]

    page.get_by_role("button", name="Cargar pedidos").click()
    page.get_by_role("button", name="Exportar").click()

    expect(page.get_by_role("status")).to_have_text("Exportación lista")
# #endregion
