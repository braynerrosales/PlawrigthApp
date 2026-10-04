from playwright.sync_api import Page, expect


# #region example
def test_cargar_los_pedidos_sin_imagenes(page: Page):
    page.route("**/*.{png,jpg,jpeg,webp}", lambda route: route.abort())

    page.goto("/")

    expect(page.get_by_text("Imagen no disponible")).to_be_visible()
    expect(page.get_by_role("row").filter(has_text="Ana Torres")).to_be_visible()
# #endregion
