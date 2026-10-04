namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class BloquearRecursosExamples : PageTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = ApiDePractica.Url };

    // #region example
    [Test]
    public async Task CargarLosPedidosSinImagenes()
    {
        await Page.RouteAsync("**/*.{png,jpg,jpeg,webp}", route => route.AbortAsync());

        await Page.GotoAsync("/");

        await Expect(Page.GetByText("Imagen no disponible")).ToBeVisibleAsync();
        await Expect(Page.GetByRole(AriaRole.Row).Filter(new() { HasText = "Ana Torres" })).ToBeVisibleAsync();
    }
    // #endregion
}
