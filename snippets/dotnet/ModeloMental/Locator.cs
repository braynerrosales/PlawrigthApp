namespace PlaywrightGuide.Snippets.ModeloMental;

public class LocatorExamples : PageTest
{
    [SetUp]
    public async Task AbrirPagina() => await Page.GotoAsync(Fixtures.Url("pedidos"));

    // #region example
    [Test]
    public async Task LocatorSeVuelveABuscar()
    {
        var cargar = Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" });
        var estado = Page.GetByRole(AriaRole.Status);
        await cargar.ClickAsync();
        await Expect(estado).ToHaveTextAsync("3 pedidos");

        var primero = Page.GetByRole(AriaRole.Listitem).First;
        await Expect(primero).ToHaveTextAsync("PED-1001 · Pagado");

        // La página reemplaza la lista: el Locator encuentra el elemento nuevo.
        await cargar.ClickAsync();
        await Expect(estado).ToHaveTextAsync("3 pedidos");
        await Expect(primero).ToHaveTextAsync("PED-1001 · Pagado"); // [!mark]
    }
    // #endregion
}
