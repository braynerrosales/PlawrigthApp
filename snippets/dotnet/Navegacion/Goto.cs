namespace PlaywrightGuide.Snippets.Navegacion;

public class GotoExamples : TiendaTest
{
    // #region example
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = "https://tienda.test" };

    [Test]
    public async Task AbrirLaPaginaDeInicio()
    {
        // La ruta es relativa a BaseURL: https://tienda.test/
        await Page.GotoAsync("/");

        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Bienvenido a Tienda QA" })).ToBeVisibleAsync();
    }
    // #endregion
}
