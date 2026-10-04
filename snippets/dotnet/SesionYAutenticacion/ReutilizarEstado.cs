using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.SesionYAutenticacion;

public class ReutilizarEstadoExamples : PortalTest
{
    private static readonly string EstadoDeAna =
        Path.Combine(Path.GetTempPath(), $"portal-qa-ana-{Guid.NewGuid()}.json");

    // Inicia sesión por la interfaz una sola vez para toda la clase y guarda el estado fuera del repositorio.
    [OneTimeSetUp]
    public async Task IniciarSesionUnaVez()
    {
        using var playwright = await global::Microsoft.Playwright.Playwright.CreateAsync();
        await using var browser = await playwright.Chromium.LaunchAsync();
        await PortalSite.SaveSessionAsync(browser, "ana", EstadoDeAna);
    }

    [OneTimeTearDown]
    public void BorrarEstado() => File.Delete(EstadoDeAna);

    // #region example
    public override BrowserNewContextOptions ContextOptions() => new()
    {
        BaseURL = Portal,
        StorageStatePath = EstadoDeAna,
    };

    [Test]
    public async Task ElPanelAbreDirectamenteConLaSesionGuardada()
    {
        // Sin pasar por /login: el contexto ya trae la cookie de sesión.
        await Page.GotoAsync("/panel");

        await Expect(Page).ToHaveURLAsync(new Regex(@"/panel$"));
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Hola, Ana" })).ToBeVisibleAsync();
    }

    [Test]
    public async Task ElClienteNoVeLaSeccionDeAdministracion()
    {
        await Page.GotoAsync("/panel");

        await Expect(Page.GetByText("Rol: cliente")).ToBeVisibleAsync();
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Administración" })).ToBeHiddenAsync();
    }
    // #endregion
}
