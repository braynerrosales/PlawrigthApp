namespace PlaywrightGuide.Snippets.Assertions;

public class WebFirstExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [Test]
    public async Task LaAsercionEsperaAQueLleguenLosDatos()
    {
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("3 pedidos");
    }
    // #endregion
}
