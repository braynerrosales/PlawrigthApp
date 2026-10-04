from playwright.sync_api import Page, expect


# #region example
def test_mostrar_los_pedidos_que_devuelve_la_api_simulada(page: Page):
    page.route(
        "**/api/pedidos",
        lambda route: route.fulfill(
            json=[
                {"id": 101, "cliente": "Cliente simulado", "producto": "Mesa", "cantidad": 1, "estado": "pendiente"},
                {"id": 102, "cliente": "Otro cliente", "producto": "Lámpara", "cantidad": 5, "estado": "enviado"},
            ],
        ),
    )

    page.goto("/")

    expect(page.get_by_role("status")).to_have_text("2 pedidos")
    expect(page.get_by_role("row").filter(has_text="Cliente simulado")).to_contain_text("Mesa")
# #endregion
