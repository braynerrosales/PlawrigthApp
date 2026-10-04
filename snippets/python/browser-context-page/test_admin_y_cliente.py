from playwright.sync_api import Browser


# #region example
def test_admin_y_cliente_no_comparten_sesion(browser: Browser):
    admin_context = browser.new_context()
    cliente_context = browser.new_context()

    admin_context.add_cookies([
        {"name": "sesion", "value": "admin-123", "url": "https://tienda-qa.example"},
    ])

    assert len(admin_context.cookies()) == 1
    assert len(cliente_context.cookies()) == 0

    admin_context.close()
    cliente_context.close()
# #endregion
