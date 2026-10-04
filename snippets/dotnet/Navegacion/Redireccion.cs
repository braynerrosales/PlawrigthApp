using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.Navegacion;

public class RedireccionExamples : TiendaTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = Tienda };

    // #region example
    [Test]
    public async Task ElLoginRedirigeALaCuenta()
    {
        await Page.GotoAsync("/login");
        await Page.GetByLabel("Usuario").FillAsync("ana");
        await Page.GetByLabel("Contraseña").FillAsync("clave-de-prueba");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Ingresar" }).ClickAsync();

        // La redirección ocurre un momento después del clic: ToHaveURLAsync reintenta hasta que la URL coincide.
        await Expect(Page).ToHaveURLAsync(new Regex(@"/cuenta$"));
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Mi cuenta" })).ToBeVisibleAsync();
    }
    // #endregion
}
