import pytest
from playwright.sync_api import Page, TimeoutError

from support import fixture


@pytest.fixture(autouse=True)
def pagina(page: Page):
    page.set_content(fixture("pedidos"))


def test_una_accion_que_nunca_puede_ejecutarse_termina_en_timeout(page: Page):
    with pytest.raises(TimeoutError, match="Timeout 2000ms exceeded"):
        # #region example
        # "Pagar" nunca se habilita: Playwright espera hasta el timeout y explica qué faltó.
        page.get_by_role("button", name="Pagar").click(timeout=2000)
        # #endregion
