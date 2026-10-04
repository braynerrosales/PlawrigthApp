namespace PlaywrightGuide.Snippets.Navegacion;

public class UrlCompletaExamples : TiendaTest
{
    // #region example
    [Test]
    public async Task VerElCatalogo()
    {
        await Page.GotoAsync("https://tienda.test/productos");
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Productos" })).ToBeVisibleAsync();
    }

    [Test]
    public async Task AbrirElInicioDeSesion()
    {
        // La misma URL completa, repetida en cada prueba: cambiar de ambiente obliga a editarlas todas.
        await Page.GotoAsync("https://tienda.test/login");
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Iniciar sesión" })).ToBeVisibleAsync();
    }
    // #endregion
}
