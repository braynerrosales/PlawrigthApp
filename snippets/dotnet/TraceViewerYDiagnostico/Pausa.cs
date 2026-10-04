namespace PlaywrightGuide.Snippets.TraceViewerYDiagnostico;

public class PausaExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [Test]
    public async Task MarcaDondeDetenerseAlDepurar()
    {
        // #region example
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();

        // Descomenta solo mientras depuras y ejecuta en modo headed (PWDEBUG=1).
        // Nunca lo subas al repositorio: en CI la prueba quedaría detenida hasta el timeout.
        // await Page.PauseAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("3 pedidos");
        // #endregion
    }
}
