import pytest
from playwright.sync_api import Playwright


@pytest.fixture
def browser_context_args(browser_context_args, api_practica):
    return {**browser_context_args, "base_url": api_practica}


@pytest.fixture
def api_request_context(playwright: Playwright, api_practica):
    """Contexto de peticiones HTTP sin navegador, con la URL base de la API de práctica."""
    request_context = playwright.request.new_context(base_url=api_practica)
    yield request_context
    request_context.dispose()
