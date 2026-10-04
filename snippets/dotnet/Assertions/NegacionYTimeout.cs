namespace PlaywrightGuide.Snippets.Assertions;

public class NegacionYTimeoutExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [Test]
    public async Task NegacionYTimeoutPropio()
    {
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();

        await Expect(Page.GetByText("Cargando…")).Not.ToBeVisibleAsync();
        await Expect(Page.GetByRole(AriaRole.Button, new() { Name = "Exportar" })).ToBeEnabledAsync(new() { Timeout = 10_000 });
    }
    // #endregion
}
