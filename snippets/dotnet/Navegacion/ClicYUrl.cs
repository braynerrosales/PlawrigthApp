using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.Navegacion;

public class ClicYUrlExamples : TiendaTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = Tienda };

    // #region example
    [Test]
    public async Task IrAProductosDesdeElMenu()
    {
        await Page.GotoAsync("/");

        await Page.GetByRole(AriaRole.Link, new() { Name = "Productos" }).ClickAsync();

        await Expect(Page).ToHaveURLAsync(new Regex(@"/productos$"));
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Productos" })).ToBeVisibleAsync();
    }
    // #endregion
}
