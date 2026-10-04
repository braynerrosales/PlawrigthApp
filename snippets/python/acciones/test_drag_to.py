import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_mover_una_tarea_a_hecho(page: Page):
    tarea = page.get_by_role("list", name="Pendiente").get_by_text("Revisar reporte")
    hecho = page.get_by_role("list", name="Hecho")

    tarea.drag_to(hecho)

    expect(hecho.get_by_role("listitem")).to_have_text(["Revisar reporte"])
# #endregion
