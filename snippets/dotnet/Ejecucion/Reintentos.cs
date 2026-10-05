namespace PlaywrightGuide.Snippets.Ejecucion;

public class ReintentosExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    private static int _intentos;

    // #region example
    [Test]
    [Retry(3)] // [!mark]
    public async Task ExportarPedidos()
    {
        // Simula un fallo intermitente: solo falla el primer intento.
        Assert.That(++_intentos, Is.GreaterThan(1), "fallo intermitente simulado"); // [!mark]

        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Page.GetByRole(AriaRole.Button, new() { Name = "Exportar" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Exportación lista");
    }
    // #endregion
}
