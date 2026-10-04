from playwright.sync_api import APIRequestContext, expect


# #region example
def test_rechazar_un_pedido_sin_cantidad_valida(api_request_context: APIRequestContext):
    response = api_request_context.post("/api/pedidos", data={"cliente": "Prueba API", "producto": "Webcam", "cantidad": 0})

    expect(response).not_to_be_ok()
    assert response.status == 400
    assert response.json() == {"errores": ["cantidad debe ser un entero mayor que 0"]}
# #endregion
