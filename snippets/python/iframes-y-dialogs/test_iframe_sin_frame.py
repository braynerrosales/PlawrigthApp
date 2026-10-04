import pytest
from playwright.sync_api import Page, TimeoutError

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("iframes-y-dialogs"))


def test_buscar_el_campo_desde_la_pagina_principal_nunca_lo_encuentra(page: Page):
    with pytest.raises(TimeoutError, match="Timeout 2000ms exceeded"):
        # #region example
        # El campo está dentro del iframe: desde `page` no existe, así que la espera nunca termina.
        page.get_by_label("Número de tarjeta").fill("4111 1111 1111 1111", timeout=2000)
        # #endregion
