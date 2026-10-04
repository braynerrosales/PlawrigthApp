import pytest
from playwright.sync_api import BrowserContext

from support import PORTAL, serve_portal


@pytest.fixture(autouse=True)
def portal(context: BrowserContext):
    serve_portal(context)


@pytest.fixture
def browser_context_args(browser_context_args):
    return {**browser_context_args, "base_url": PORTAL}
