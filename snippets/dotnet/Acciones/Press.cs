namespace PlaywrightGuide.Snippets.Acciones;

public class PressExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task BuscarConEnter()
    {
        var buscador = Page.GetByRole(AriaRole.Searchbox, new() { Name = "Buscar en la ayuda" });

        await buscador.FillAsync("facturas");
        await buscador.PressAsync("Enter");

        await Expect(Page.GetByText("Resultados para «facturas»")).ToBeVisibleAsync();
    }
    // #endregion
}
