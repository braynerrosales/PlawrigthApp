# #region example
import re

from playwright.sync_api import Page, expect


def test_pytest_playwright_entrega_la_page_lista(page: Page):
    page.goto("https://playwright.dev/")

    expect(page).to_have_title(re.compile("Playwright"))
# #endregion
