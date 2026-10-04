import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("upload-y-download"))


# #region example
def test_subir_una_foto_con_un_boton_personalizado(page: Page):
    # El bloque with empieza a esperar el diálogo antes del clic que lo abre.
    with page.expect_file_chooser() as file_chooser_info:
        page.get_by_role("button", name="Subir foto").click()
    file_chooser = file_chooser_info.value

    file_chooser.set_files({"name": "perfil.png", "mimeType": "image/png", "buffer": b"foto"})

    expect(page.get_by_text("Foto: perfil.png")).to_be_visible()
# #endregion
