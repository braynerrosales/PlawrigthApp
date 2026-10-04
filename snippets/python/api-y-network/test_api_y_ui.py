import time

from playwright.sync_api import Page, expect


# #region example
def test_un_pedido_creado_por_api_aparece_en_la_tabla(page: Page):
    cliente = f"Cliente {time.time_ns()}"
    response = page.request.post("/api/pedidos", data={"cliente": cliente, "producto": "Auriculares", "cantidad": 1})
    assert response.status == 201

    page.goto("/")

    fila = page.get_by_role("row").filter(has_text=cliente)
    expect(fila).to_contain_text("Auriculares")
# #endregion
