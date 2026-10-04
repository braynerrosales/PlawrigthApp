from playwright.sync_api import Page


# #region example
def test_crear_un_pedido_esperando_un_tiempo_fijo(page: Page):
    page.goto("/")
    page.get_by_label("Cliente").fill("Prueba UI")
    page.get_by_label("Producto").fill("Silla")
    page.get_by_role("button", name="Crear pedido").click()

    # «Dos segundos deberían bastar»: sobra casi siempre y, el día que la API tarda más, falla.
    page.wait_for_timeout(2_000)
    mensaje = page.get_by_role("alert").text_content()
    assert "creado" in mensaje
# #endregion
