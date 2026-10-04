namespace PlaywrightGuide.Snippets.TraceViewerYDiagnostico;

public class LeerElErrorExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [Test]
    public void UnaAsercionQueFallaExplicaQueEsperabaYQueEncontro()
    {
        var error = Assert.CatchAsync(async () =>
        {
            // #region example
            // Falta un paso: nadie hizo clic en "Cargar pedidos", así que la lista sigue vacía.
            await Expect(Page.GetByRole(AriaRole.List, new() { Name = "Pedidos" }).GetByRole(AriaRole.Listitem))
                .ToHaveCountAsync(3, new() { Timeout = 2000 });
            // #endregion
        });
        Assert.That(error!.Message, Does.Contain("Locator expected to have count"));
    }
}
