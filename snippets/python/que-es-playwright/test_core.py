from concurrent.futures import ThreadPoolExecutor

# #region example
from playwright.sync_api import sync_playwright


def main():
    with sync_playwright() as playwright:
        browser = playwright.chromium.launch()
        context = browser.new_context()
        page = context.new_page()

        page.goto("https://playwright.dev/")
        print(page.title())

        browser.close()
# #endregion


def test_playwright_core_sin_test_runner():
    # pytest-playwright ya tiene un loop de Playwright en este hilo: el script corre en otro, como si fuera independiente.
    with ThreadPoolExecutor(max_workers=1) as hilo:
        hilo.submit(main).result()
