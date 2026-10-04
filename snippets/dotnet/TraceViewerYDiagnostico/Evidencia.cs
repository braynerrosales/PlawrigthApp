namespace PlaywrightGuide.Snippets.TraceViewerYDiagnostico;

public class EvidenciaExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [Test]
    public async Task AdjuntaUnaCapturaComoEvidenciaAlReporte()
    {
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("3 pedidos");
        // #region example
        var captura = Path.Combine(Path.GetTempPath(), $"pedidos-cargados-{Guid.NewGuid()}.png");
        await Page.ScreenshotAsync(new() { Path = captura, FullPage = true });
        TestContext.AddTestAttachment(captura, "pedidos cargados");
        // #endregion
        Assert.That(File.Exists(captura), Is.True);
    }
}
