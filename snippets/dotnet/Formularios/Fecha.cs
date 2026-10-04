namespace PlaywrightGuide.Snippets.Formularios;

public class FechaExamples : FixtureTest
{
    protected override string Fixture => "formularios";

    // #region example
    [Test]
    public async Task CompletarUnCampoDeFecha()
    {
        var fecha = Page.GetByLabel("Fecha de entrega");

        // Siempre en formato ISO (yyyy-mm-dd), sin importar cómo lo muestre el navegador.
        await fecha.FillAsync("2026-10-04");

        await Expect(fecha).ToHaveValueAsync("2026-10-04");
        await Expect(Page.GetByText("Entrega: 04/10/2026")).ToBeVisibleAsync();
    }
    // #endregion
}
