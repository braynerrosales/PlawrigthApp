namespace PlaywrightGuide.Snippets.AutoWaiting;

public class TimeoutExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [Test]
    public void UnaAccionQueNuncaPuedeEjecutarseTerminaEnTimeout()
    {
        var error = Assert.CatchAsync<TimeoutException>(async () =>
        {
            // #region example
            // "Pagar" nunca se habilita: Playwright espera hasta el timeout y explica qué faltó.
            await Page.GetByRole(AriaRole.Button, new() { Name = "Pagar" }).ClickAsync(new() { Timeout = 2000 });
            // #endregion
        });
        Assert.That(error!.Message, Does.Contain("Timeout 2000ms exceeded"));
    }
}
