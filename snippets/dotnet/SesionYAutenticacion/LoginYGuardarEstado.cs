using System.Text.Json;

namespace PlaywrightGuide.Snippets.SesionYAutenticacion;

public class LoginYGuardarEstadoExamples : PortalTest
{
    // Fuera del repositorio: el archivo contiene la sesión.
    private readonly string _ruta = Path.Combine(Path.GetTempPath(), $"portal-qa-ana-{Guid.NewGuid()}.json");

    [TearDown]
    public void BorrarEstado() => File.Delete(_ruta);

    // #region example
    [Test]
    public async Task IniciarSesionUnaVezYGuardarElEstado()
    {
        await Page.GotoAsync("/login");
        await Page.GetByLabel("Usuario").FillAsync("ana");
        await Page.GetByLabel("Contraseña").FillAsync("clave-de-prueba");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Ingresar" }).ClickAsync();
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Hola, Ana" })).ToBeVisibleAsync();

        // Escribe cookies y localStorage en el archivo y además devuelve el mismo estado como JSON.
        var estado = await Context.StorageStateAsync(new() { Path = _ruta });

        using var json = JsonDocument.Parse(estado);
        var cookies = json.RootElement.GetProperty("cookies").EnumerateArray();
        Assert.That(cookies.Any(cookie => cookie.GetProperty("name").GetString() == "sesion"), Is.True);
        var guardado = json.RootElement.GetProperty("origins")[0].GetProperty("localStorage")[0];
        Assert.That(guardado.GetProperty("name").GetString(), Is.EqualTo("ultimoUsuario"));
        Assert.That(File.Exists(_ruta), Is.True);
    }
    // #endregion
}
