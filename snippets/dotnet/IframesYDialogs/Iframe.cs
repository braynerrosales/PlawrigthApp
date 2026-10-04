namespace PlaywrightGuide.Snippets.IframesYDialogs;

public class IframeExamples : FixtureTest
{
    protected override string Fixture => "iframes-y-dialogs";

    // #region example
    [Test]
    public async Task ElPagoSeCompletaDentroDelIframe()
    {
        var pago = Page.FrameLocator("iframe[title=\"Pago con tarjeta\"]");

        await pago.GetByLabel("Número de tarjeta").FillAsync("4111 1111 1111 1111");
        await pago.GetByRole(AriaRole.Button, new() { Name = "Pagar" }).ClickAsync();

        await Expect(pago.GetByRole(AriaRole.Status)).ToHaveTextAsync("Pago aprobado");
    }
    // #endregion
}
