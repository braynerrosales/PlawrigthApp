namespace PlaywrightGuide.Snippets.Acciones;

public class SelectOptionExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task ElegirUnaOpcionPorSuTexto()
    {
        var pais = Page.GetByLabel("País");

        await pais.SelectOptionAsync(new SelectOptionValue { Label = "Chile" });

        await Expect(pais).ToHaveValueAsync("cl");
    }
    // #endregion
}
