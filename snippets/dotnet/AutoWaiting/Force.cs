namespace PlaywrightGuide.Snippets.AutoWaiting;

public class ForceExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [Test]
    public async Task ForceHaceClicAunqueElBotonEsteDeshabilitado()
    {
        // #region example
        // "Pagar" está deshabilitado. Force se salta las comprobaciones: el clic "pasa" y no hace nada.
        await Page.GetByRole(AriaRole.Button, new() { Name = "Pagar" }).ClickAsync(new() { Force = true });
        // #endregion
        await Expect(Page.GetByRole(AriaRole.Status)).ToBeEmptyAsync();
    }
}
