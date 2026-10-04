import pytest
from playwright.sync_api import Page, TimeoutError

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("upload-y-download"))


def test_esperar_la_descarga_despues_del_clic_llega_tarde(page: Page):
    with pytest.raises(TimeoutError, match="Timeout 2000ms exceeded"):
        # #region example
        page.get_by_role("button", name="Exportar CSV").click()
        # La descarga ya empezó durante el clic: esta espera no la ve y vence.
        download = page.wait_for_event("download", timeout=2000)
        # #endregion
        assert download
