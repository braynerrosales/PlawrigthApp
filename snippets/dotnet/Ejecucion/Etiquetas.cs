namespace PlaywrightGuide.Snippets.Ejecucion;

public class EtiquetasExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [Test]
    [Category("smoke")] // [!mark]
    public async Task ExportarPedidos()
    {
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Page.GetByRole(AriaRole.Button, new() { Name = "Exportar" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Exportación lista");
    }
    // #endregion
}
