namespace PlaywrightGuide.Snippets.AutoWaiting;

public class AutoWaitExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [Test]
    public async Task ExportarCuandoLosDatosEstanListos()
    {
        // Al empezar, un aviso tapa la página y Exportar está deshabilitado hasta que llegan los datos.
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Page.GetByRole(AriaRole.Button, new() { Name = "Exportar" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Exportación lista");
    }
    // #endregion
}
