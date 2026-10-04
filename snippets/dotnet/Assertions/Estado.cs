namespace PlaywrightGuide.Snippets.Assertions;

public class EstadoExamples : FixtureTest
{
    protected override string Fixture => "locators";

    // #region example
    [Test]
    public async Task VisibilidadYEstado()
    {
        var monitor = Page.GetByRole(AriaRole.Listitem).Filter(new() { HasText = "Monitor 4K" });

        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Productos" })).ToBeVisibleAsync();
        await Expect(Page.GetByText("Cambios guardados")).ToBeHiddenAsync();
        await Expect(monitor.GetByRole(AriaRole.Button)).ToBeDisabledAsync();
        await Expect(Page.GetByRole(AriaRole.Button, new() { Name = "Guardar" })).ToBeEnabledAsync();
    }
    // #endregion
}
