namespace PlaywrightGuide.Snippets.TraceViewerYDiagnostico;

public class GrabarTraceExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [SetUp]
    public async Task StartTracing()
    {
        await Context.Tracing.StartAsync(new()
        {
            Title = $"{TestContext.CurrentContext.Test.ClassName}.{TestContext.CurrentContext.Test.Name}",
            Screenshots = true,
            Snapshots = true,
            Sources = true,
        });
    }

    [TearDown]
    public async Task StopTracing()
    {
        var outcome = TestContext.CurrentContext.Result.Outcome.Status;
        var failed = outcome is NUnit.Framework.Interfaces.TestStatus.Failed;

        // Con Path = null el trace se descarta: solo se guarda el de las pruebas que fallaron.
        await Context.Tracing.StopAsync(new()
        {
            Path = failed
                ? Path.Combine(Path.GetTempPath(), "playwright-traces",
                    $"{TestContext.CurrentContext.Test.ClassName}.{TestContext.CurrentContext.Test.Name}.zip")
                : null,
        });
    }
    // #endregion

    [Test]
    public async Task CargaLosPedidosConElTraceActivo()
    {
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("3 pedidos");
    }
}
