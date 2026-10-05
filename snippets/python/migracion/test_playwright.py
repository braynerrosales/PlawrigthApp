from playwright.sync_api import Page, expect

from support import fixture_url


# #region example
def test_exportar_pedidos(page: Page):
    page.goto(fixture_url("pedidos"))
    page.get_by_role("button", name="Cargar pedidos").click()
    page.get_by_role("button", name="Exportar").click()

    expect(page.get_by_role("status")).to_have_text("Exportación lista")
    expect(page.get_by_role("listitem")).to_have_count(3)
# #endregion
