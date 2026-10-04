from playwright.sync_api import BrowserContext, Page, expect

from support import fixture


def test_graba_un_trace_de_un_flujo_con_la_tracing_api(page: Page, context: BrowserContext, tmp_path):
    trace_path = tmp_path / "pedidos-trace.zip"
    # #region example
    context.tracing.start(screenshots=True, snapshots=True, sources=True)
    try:
        page.set_content(fixture("pedidos"))
        page.get_by_role("button", name="Cargar pedidos").click()
        expect(page.get_by_role("status")).to_have_text("3 pedidos")
    finally:
        # Sin stop no hay archivo: el trace queda en memoria y se pierde.
        context.tracing.stop(path=trace_path)
    # #endregion
    assert trace_path.exists()
