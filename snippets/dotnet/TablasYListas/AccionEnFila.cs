namespace PlaywrightGuide.Snippets.TablasYListas;

public class AccionEnFilaExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    // #region example
    [Test]
    public async Task CancelarUnPedidoDesdeSuFila()
    {
        // La fila se identifica por un dato que el usuario reconoce, no por su posición.
        var fila = Page.GetByRole(AriaRole.Row).Filter(new() { HasText = "PED-1003" });

        // Todas las filas tienen un botón "Cancelar": se busca dentro de la fila.
        await fila.GetByRole(AriaRole.Button, new() { Name = "Cancelar" }).ClickAsync();

        await Expect(fila).ToContainTextAsync("Cancelado");
        await Expect(fila.GetByRole(AriaRole.Button, new() { Name = "Cancelar" })).ToBeDisabledAsync();
    }
    // #endregion
}
