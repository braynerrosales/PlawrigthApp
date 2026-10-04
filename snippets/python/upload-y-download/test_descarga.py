from pathlib import Path

import pytest
from playwright.sync_api import Page

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("upload-y-download"))


# #region example
def test_descargar_el_csv_y_comprobar_su_contenido(page: Page):
    # El bloque with empieza a esperar antes del clic y entrega la descarga al salir.
    with page.expect_download() as download_info:
        page.get_by_role("button", name="Exportar CSV").click()
    download = download_info.value

    assert download.suggested_filename == "pedidos.csv"

    # path() espera a que termine la descarga; el archivo se borra al cerrar el contexto.
    csv = Path(download.path()).read_text(encoding="utf-8")
    assert csv.split("\n")[0] == "id,cliente,total"
    assert "1,Ana Pérez,120.00" in csv
# #endregion
