namespace PlaywrightGuide.Snippets.Acciones;

public class FillExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task CompletarYLimpiarCampos()
    {
        var nombre = Page.GetByLabel("Nombre");

        await nombre.FillAsync("Ana Pérez");
        await Expect(nombre).ToHaveValueAsync("Ana Pérez");

        await nombre.ClearAsync();
        await Expect(nombre).ToHaveValueAsync("");
    }
    // #endregion
}
