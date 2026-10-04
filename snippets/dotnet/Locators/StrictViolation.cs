namespace PlaywrightGuide.Snippets.Locators;

public class StrictViolationExamples : LocatorsTest
{
    [Test]
    public void AccionSobreLocatorAmbiguoFalla()
    {
        var error = Assert.CatchAsync<PlaywrightException>(async () =>
        {
            // #region example
            // Hay 3 botones con ese nombre: Playwright se niega a adivinar.
            await Page.GetByRole(AriaRole.Button, new() { Name = "Agregar al carrito" }).ClickAsync();
            // #endregion
        });
        Assert.That(error!.Message, Does.Contain("strict mode violation"));
    }
}
