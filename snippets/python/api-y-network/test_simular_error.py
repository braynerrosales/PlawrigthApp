from playwright.sync_api import Page, expect


# #region example
def test_avisar_cuando_la_api_falla(page: Page):
    page.route(
        "**/api/pedidos",
        lambda route: route.fulfill(status=500, json={"errores": ["error interno"]}),
    )

    page.goto("/")

    expect(page.get_by_role("status")).to_have_text("No se pudieron cargar los pedidos. Inténtalo de nuevo más tarde.")
    expect(page.get_by_role("table")).to_be_hidden()
# #endregion
