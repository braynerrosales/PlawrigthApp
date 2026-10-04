namespace PlaywrightGuide.Snippets.Acciones;

public class DblclickExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task RenombrarConDobleClic()
    {
        await Page.GetByText("Mi lista").DblClickAsync();

        var nombre = Page.GetByRole(AriaRole.Textbox, new() { Name = "Nombre de la lista" });
        await Expect(nombre).ToBeFocusedAsync();
        await nombre.FillAsync("Pruebas de regresión");
        await Expect(nombre).ToHaveValueAsync("Pruebas de regresión");
    }
    // #endregion
}
