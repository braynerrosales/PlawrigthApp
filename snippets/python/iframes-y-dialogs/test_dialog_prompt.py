import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("iframes-y-dialogs"))


# #region example
def test_responder_al_prompt_renombra_la_tarea(page: Page):
    tareas = page.get_by_role("list", name="Tareas")

    # accept con texto es la respuesta que escribiría la persona en el prompt.
    page.once("dialog", lambda dialog: dialog.accept("Revisar reporte final"))
    (
        tareas.get_by_role("listitem")
        .filter(has_text="Revisar reporte")
        .get_by_role("button", name="Renombrar")
        .click()
    )

    expect(tareas.get_by_role("listitem").first).to_contain_text("Revisar reporte final")
# #endregion
