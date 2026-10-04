import re
from pathlib import Path

import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("upload-y-download"))


# #region example
def test_subir_un_archivo_desde_el_disco(page: Page):
    # Ruta absoluta construida desde este archivo: no depende de dónde se ejecute la prueba.
    factura = Path(__file__).parent / "../../fixtures/archivos/factura.txt"

    page.get_by_label("Adjuntos").set_input_files(factura)

    archivos = page.get_by_role("list", name="Archivos seleccionados").get_by_role("listitem")
    expect(archivos).to_have_text([re.compile(r"^factura\.txt \(\d+ bytes\)$")])
# #endregion
