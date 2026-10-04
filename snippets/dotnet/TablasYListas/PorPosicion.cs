namespace PlaywrightGuide.Snippets.TablasYListas;

public class PorPosicionExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    [Test]
    public async Task LeerUnaCeldaPorSuPosicion()
    {
        // #region example
        // "Fila 3, columna 2": hoy es el cliente de PED-1003.
        var cliente = Page.Locator("tbody tr:nth-child(3) td:nth-child(2)");
        await Expect(cliente).ToHaveTextAsync("Elena Mora");
        // #endregion

        // Fuera del ejemplo: al ordenar por cliente, la misma posición es otro pedido.
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cliente" }).ClickAsync();
        await Expect(cliente).ToHaveTextAsync("Carla Gómez");
    }
}
