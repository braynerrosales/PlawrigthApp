namespace PlaywrightGuide.Snippets.AutoWaiting;

public class EsperarCondicionExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [Test]
    public async Task EsperarElResultadoNoElTiempo()
    {
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Listitem)).ToHaveCountAsync(3);
    }
    // #endregion
}
