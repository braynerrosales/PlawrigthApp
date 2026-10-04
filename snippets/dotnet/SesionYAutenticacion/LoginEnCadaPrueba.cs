using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.SesionYAutenticacion;

public class LoginEnCadaPruebaExamples : PortalTest
{
    // #region example
    [SetUp]
    public async Task IniciarSesion()
    {
        // Cada prueba repite el formulario de login antes de hacer lo que de verdad quiere probar.
        await Page.GotoAsync("/login");
        await Page.GetByLabel("Usuario").FillAsync("ana");
        await Page.GetByLabel("Contraseña").FillAsync("clave-de-prueba");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Ingresar" }).ClickAsync();
        await Expect(Page).ToHaveURLAsync(new Regex(@"/panel$"));
    }

    [Test]
    public async Task VerElSaludo() =>
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Hola, Ana" })).ToBeVisibleAsync();

    [Test]
    public async Task VerElRol() =>
        await Expect(Page.GetByText("Rol: cliente")).ToBeVisibleAsync();
    // #endregion
}
