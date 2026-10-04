namespace PlaywrightGuide.Snippets.SesionYAutenticacion;

public class DosRolesExamples : BrowserTest
{
    // Fuera del repositorio: los archivos contienen las sesiones.
    private readonly string _estadoDeAdmin = Path.Combine(Path.GetTempPath(), $"portal-qa-admin-{Guid.NewGuid()}.json");
    private readonly string _estadoDeAna = Path.Combine(Path.GetTempPath(), $"portal-qa-ana-{Guid.NewGuid()}.json");

    [SetUp]
    public async Task GuardarSesiones()
    {
        await PortalSite.SaveSessionAsync(Browser, "admin", _estadoDeAdmin);
        await PortalSite.SaveSessionAsync(Browser, "ana", _estadoDeAna);
    }

    [TearDown]
    public void BorrarEstados()
    {
        File.Delete(_estadoDeAdmin);
        File.Delete(_estadoDeAna);
    }

    // #region example
    [Test]
    public async Task AdminYClienteEnLaMismaPrueba()
    {
        var adminContext = await Browser.NewContextAsync(new()
        {
            BaseURL = PortalSite.Url,
            StorageStatePath = _estadoDeAdmin,
        });
        var clienteContext = await Browser.NewContextAsync(new()
        {
            BaseURL = PortalSite.Url,
            StorageStatePath = _estadoDeAna,
        });
        await PortalSite.ServeAsync(adminContext);
        await PortalSite.ServeAsync(clienteContext);

        var adminPage = await adminContext.NewPageAsync();
        var clientePage = await clienteContext.NewPageAsync();
        await adminPage.GotoAsync("/panel");
        await clientePage.GotoAsync("/panel");

        await Expect(adminPage.GetByRole(AriaRole.Heading, new() { Name = "Administración" })).ToBeVisibleAsync();
        await Expect(clientePage.GetByRole(AriaRole.Heading, new() { Name = "Hola, Ana" })).ToBeVisibleAsync();
        await Expect(clientePage.GetByRole(AriaRole.Heading, new() { Name = "Administración" })).ToBeHiddenAsync();

        await adminContext.CloseAsync();
        await clienteContext.CloseAsync();
    }
    // #endregion
}
