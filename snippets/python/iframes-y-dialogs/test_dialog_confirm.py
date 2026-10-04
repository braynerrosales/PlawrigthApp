import pytest
from playwright.sync_api import Dialog, Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("iframes-y-dialogs"))


# #region example
def test_aceptar_el_confirm_elimina_la_tarea(page: Page):
    tarea = page.get_by_role("listitem").filter(has_text="Revisar reporte")

    # El handler se registra ANTES de la acción que abre el diálogo.
    mensajes: list[str] = []

    def aceptar(dialog: Dialog):
        mensajes.append(dialog.message)
        dialog.accept()

    page.once("dialog", aceptar)
    tarea.get_by_role("button", name="Eliminar").click()

    expect(tarea).to_be_hidden()
    assert mensajes == ["¿Eliminar «Revisar reporte»?"]
# #endregion
