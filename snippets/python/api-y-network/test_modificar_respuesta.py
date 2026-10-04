from playwright.sync_api import Page, Route, expect


# #region example
def test_mostrar_los_pedidos_como_enviados(page: Page):
    def marcar_como_enviados(route: Route):
        response = route.fetch()
        pedidos = response.json()
        for pedido in pedidos:
            pedido["estado"] = "enviado"
        route.fulfill(response=response, json=pedidos)

    page.route("**/api/pedidos", marcar_como_enviados)

    page.goto("/")

    expect(page.get_by_role("row").filter(has_text="Ana Torres")).to_contain_text("enviado")
# #endregion
