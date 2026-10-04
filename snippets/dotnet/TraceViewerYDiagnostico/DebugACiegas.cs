namespace PlaywrightGuide.Snippets.TraceViewerYDiagnostico;

public class DebugACiegasExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [Test]
    public async Task DepurarSubiendoEsperasYTimeouts()
    {
        // #region example
        // "Falló una vez en CI": se agrega una espera, se sube el timeout y se imprime el estado.
        await Page.WaitForTimeoutAsync(1000);
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync(new() { Timeout = 60_000 });
        Console.WriteLine(await Page.GetByRole(AriaRole.Status).TextContentAsync());
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("3 pedidos", new() { Timeout = 60_000 });
        // #endregion
    }
}
