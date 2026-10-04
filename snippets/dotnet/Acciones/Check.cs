namespace PlaywrightGuide.Snippets.Acciones;

public class CheckExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task MarcarCheckboxesYRadios()
    {
        await Page.GetByLabel("Acepto los términos").CheckAsync();
        await Page.GetByLabel("Recibir novedades").UncheckAsync();
        await Page.GetByRole(AriaRole.Radio, new() { Name = "Pro" }).CheckAsync();

        await Expect(Page.GetByLabel("Acepto los términos")).ToBeCheckedAsync();
        await Expect(Page.GetByLabel("Recibir novedades")).Not.ToBeCheckedAsync();
        await Expect(Page.GetByRole(AriaRole.Radio, new() { Name = "Gratis" })).Not.ToBeCheckedAsync();
    }
    // #endregion
}
