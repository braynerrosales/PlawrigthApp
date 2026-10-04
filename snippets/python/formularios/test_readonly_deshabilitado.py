import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("formularios"))


# #region example
def test_campos_de_solo_lectura_y_deshabilitados(page: Page):
    pedido = page.get_by_label("Número de pedido")
    codigo = page.get_by_label("Código de descuento")

    # readonly: se ve y se envía, pero no se puede editar.
    expect(pedido).to_have_value("PED-1042")
    expect(pedido).not_to_be_editable()

    # disabled: la app lo habilita solo cuando marcas la casilla.
    expect(codigo).to_be_disabled()
    page.get_by_label("Tengo un cupón").check()
    expect(codigo).to_be_enabled()
    codigo.fill("QA10")
    expect(codigo).to_have_value("QA10")
# #endregion
