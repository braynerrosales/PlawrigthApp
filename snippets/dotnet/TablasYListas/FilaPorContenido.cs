namespace PlaywrightGuide.Snippets.TablasYListas;

public class FilaPorContenidoExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    // #region example
    [Test]
    public async Task LaFilaPorSuContenidoSigueSiendoLaMismaAlOrdenar()
    {
        var pedido = Page.GetByRole(AriaRole.Row).Filter(new() { HasText = "PED-1003" });
        await Expect(pedido).ToContainTextAsync("Elena Mora");

        // La tabla se ordena y se vuelve a dibujar: la fila cambia de posición.
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cliente" }).ClickAsync();

        // El locator se resuelve otra vez y encuentra la misma fila de datos.
        await Expect(pedido).ToContainTextAsync("Elena Mora");
    }
    // #endregion
}
