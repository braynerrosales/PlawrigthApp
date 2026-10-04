namespace PlaywrightGuide.Snippets.TraceViewerYDiagnostico;

public class PasosExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [SetUp]
    public async Task StartTracing() =>
        await Context.Tracing.StartAsync(new() { Screenshots = true, Snapshots = true, Sources = true });

    // Sin Path, el trace se descarta; aquí solo interesa que los grupos funcionen.
    [TearDown]
    public async Task StopTracing() => await Context.Tracing.StopAsync();

    [Test]
    public async Task AgrupaLasAccionesEnElTrace()
    {
        // #region example
        await Context.Tracing.GroupAsync("Cargar los pedidos");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("3 pedidos");
        await Context.Tracing.GroupEndAsync();

        await Context.Tracing.GroupAsync("Exportar");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Exportar" }).ClickAsync();
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Exportación lista");
        await Context.Tracing.GroupEndAsync();
        // #endregion
    }
}
