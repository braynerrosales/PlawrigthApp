import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("acciones"))


# #region example
def test_autocompletar_tecla_por_tecla(page: Page):
    # El autocompletado escucha eventos de teclado; fill() no los genera.
    page.get_by_label("Ciudad").press_sequentially("Bue")

    sugerencias = page.get_by_role("listbox", name="Sugerencias")
    expect(sugerencias.get_by_role("option")).to_have_text(["Buenos Aires"])
# #endregion
