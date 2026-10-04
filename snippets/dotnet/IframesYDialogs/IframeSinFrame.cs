namespace PlaywrightGuide.Snippets.IframesYDialogs;

public class IframeSinFrameExamples : FixtureTest
{
    protected override string Fixture => "iframes-y-dialogs";

    [Test]
    public void BuscarElCampoDesdeLaPaginaPrincipalNuncaLoEncuentra()
    {
        var error = Assert.CatchAsync<TimeoutException>(async () =>
        {
            // #region example
            // El campo está dentro del iframe: desde Page no existe, así que la espera nunca termina.
            await Page.GetByLabel("Número de tarjeta").FillAsync("4111 1111 1111 1111", new() { Timeout = 2000 });
            // #endregion
        });
        Assert.That(error!.Message, Does.Contain("Timeout 2000ms exceeded"));
    }
}
