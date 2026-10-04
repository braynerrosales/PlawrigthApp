import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


def test_force_hace_clic_aunque_el_boton_este_deshabilitado(page: Page):
    # #region example
    # "Pagar" está deshabilitado. force se salta las comprobaciones: el clic "pasa" y no hace nada.
    page.get_by_role("button", name="Pagar").click(force=True)
    # #endregion
    expect(page.get_by_role("status")).to_be_empty()
