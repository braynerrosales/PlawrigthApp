from playwright.sync_api import Page, expect


# #region example
def test_crear_un_pedido_desde_el_formulario(page: Page):
    page.goto("/")
    page.get_by_label("Cliente").fill("Prueba UI")
    page.get_by_label("Producto").fill("Silla")
    page.get_by_label("Cantidad").fill("4")

    with page.expect_response(
        lambda response: response.url.endswith("/api/pedidos") and response.request.method == "POST",
    ) as response_info:
        page.get_by_role("button", name="Crear pedido").click()
    response = response_info.value

    assert response.status == 201
    pedido_id = response.json()["id"]
    expect(page.get_by_role("alert")).to_have_text(f"Pedido #{pedido_id} creado")
# #endregion
