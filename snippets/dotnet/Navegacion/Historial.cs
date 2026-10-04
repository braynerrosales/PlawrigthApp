using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.Navegacion;

public class HistorialExamples : TiendaTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = Tienda };

    // #region example
    [Test]
    public async Task VolverAtrasYAdelanteEnElHistorial()
    {
        await Page.GotoAsync("/");
        await Page.GetByRole(AriaRole.Link, new() { Name = "Productos" }).ClickAsync();
        await Expect(Page).ToHaveURLAsync(new Regex(@"/productos$"));

        await Page.GoBackAsync();
        await Expect(Page).ToHaveURLAsync("https://tienda.test/");
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Bienvenido a Tienda QA" })).ToBeVisibleAsync();

        await Page.GoForwardAsync();
        await Expect(Page).ToHaveURLAsync(new Regex(@"/productos$"));
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Productos" })).ToBeVisibleAsync();
    }
    // #endregion
}
