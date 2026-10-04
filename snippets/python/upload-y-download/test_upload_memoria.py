import pytest
from playwright.sync_api import Page, expect

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("upload-y-download"))


# #region example
def test_subir_varios_archivos_creados_en_memoria_y_quitarlos(page: Page):
    adjuntos = page.get_by_label("Adjuntos")
    archivos = page.get_by_role("list", name="Archivos seleccionados").get_by_role("listitem")

    adjuntos.set_input_files([
        {"name": "notas.txt", "mimeType": "text/plain", "buffer": b"Entregar por la tarde"},
        {"name": "datos.csv", "mimeType": "text/csv", "buffer": b"id,total\n1,120.00\n"},
    ])
    expect(archivos).to_have_text(["notas.txt (21 bytes)", "datos.csv (18 bytes)"])

    # Una lista vacía deja el input sin archivos.
    adjuntos.set_input_files([])
    expect(archivos).to_have_count(0)
# #endregion
