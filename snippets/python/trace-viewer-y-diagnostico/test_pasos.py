import pytest
from playwright.sync_api import BrowserContext, Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def trace(page: Page, context: BrowserContext):
    page.set_content(fixture("pedidos"))
    context.tracing.start(screenshots=True, snapshots=True, sources=True)
    yield
    # Sin path, el trace se descarta; aquí solo interesa que los grupos funcionen.
    context.tracing.stop()


def test_agrupa_las_acciones_en_el_trace(page: Page, context: BrowserContext):
    # #region example
    context.tracing.group("Cargar los pedidos")
    page.get_by_role("button", name="Cargar pedidos").click()
    expect(page.get_by_role("status")).to_have_text("3 pedidos")
    context.tracing.group_end()

    context.tracing.group("Exportar")
    page.get_by_role("button", name="Exportar").click()
    expect(page.get_by_role("status")).to_have_text("Exportación lista")
    context.tracing.group_end()
    # #endregion
