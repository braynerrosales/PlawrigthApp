from playwright.sync_api import APIRequestContext, expect


# #region example
def test_crear_leer_y_eliminar_un_pedido(api_request_context: APIRequestContext):
    creado = api_request_context.post("/api/pedidos", data={"cliente": "Prueba API", "producto": "Webcam", "cantidad": 2})
    assert creado.status == 201
    pedido_id = creado.json()["id"]

    leido = api_request_context.get(f"/api/pedidos/{pedido_id}")
    expect(leido).to_be_ok()
    pedido = leido.json()
    assert (pedido["cliente"], pedido["cantidad"], pedido["estado"]) == ("Prueba API", 2, "pendiente")

    eliminado = api_request_context.delete(f"/api/pedidos/{pedido_id}")
    assert eliminado.status == 204
    assert api_request_context.get(f"/api/pedidos/{pedido_id}").status == 404
# #endregion
