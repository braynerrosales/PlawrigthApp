from playwright.sync_api import APIRequestContext, expect


# #region example
def test_consultar_un_pedido_por_la_api(api_request_context: APIRequestContext):
    response = api_request_context.get("/api/pedidos/1")

    expect(response).to_be_ok()
    pedido = response.json()
    assert pedido["id"] == 1
    assert pedido["cliente"] == "Ana Torres"
    assert pedido["producto"] == "Teclado"
# #endregion
