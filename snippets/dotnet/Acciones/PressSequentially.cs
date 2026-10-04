namespace PlaywrightGuide.Snippets.Acciones;

public class PressSequentiallyExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task AutocompletarTeclaPorTecla()
    {
        // El autocompletado escucha eventos de teclado; FillAsync no los genera.
        await Page.GetByLabel("Ciudad").PressSequentiallyAsync("Bue");

        var sugerencias = Page.GetByRole(AriaRole.Listbox, new() { Name = "Sugerencias" });
        await Expect(sugerencias.GetByRole(AriaRole.Option)).ToHaveTextAsync(new[] { "Buenos Aires" });
    }
    // #endregion
}
