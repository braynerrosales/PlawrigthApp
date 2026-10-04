namespace PlaywrightGuide.Snippets.AutoWaiting;

public class SleepExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [Test]
    public async Task EsperaFijaAntesDeActuar()
    {
        await Page.WaitForTimeoutAsync(1000); // "por si el aviso no se fue"
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Page.WaitForTimeoutAsync(2000); // "por si los datos tardan"

        await Expect(Page.GetByRole(AriaRole.Listitem)).ToHaveCountAsync(3);
    }
    // #endregion
}
