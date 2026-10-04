import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("iframes-y-dialogs"))


# #region example
def test_registrar_el_handler_despues_del_clic_llega_tarde(page: Page):
    tarea = page.get_by_role("listitem").filter(has_text="Revisar reporte")

    tarea.get_by_role("button", name="Eliminar").click()
    # Demasiado tarde: sin listener, Playwright ya descartó el confirm (confirm devolvió false).
    page.once("dialog", lambda dialog: dialog.accept())

    # La tarea no se eliminó.
    expect(tarea).to_be_visible()
# #endregion
