namespace PlaywrightGuide.Snippets.Acciones;

public class HoverExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task MostrarUnTooltip()
    {
        await Page.GetByRole(AriaRole.Button, new() { Name = "Más información" }).HoverAsync();

        await Expect(Page.GetByRole(AriaRole.Tooltip)).ToHaveTextAsync("Respondemos en menos de 24 horas");
    }
    // #endregion
}
