using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.SesionYAutenticacion;

public class LeerCookiesExamples : PortalTest
{
    // #region example
    [Test]
    public async Task LaCookieDeSesionEsHttpOnlyYSecure()
    {
        await Page.GotoAsync("/login");
        await Page.GetByLabel("Usuario").FillAsync("ana");
        await Page.GetByLabel("Contraseña").FillAsync("clave-de-prueba");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Ingresar" }).ClickAsync();
        await Expect(Page).ToHaveURLAsync(new Regex(@"/panel$"));

        var cookies = await Context.CookiesAsync(Portal);
        var sesion = cookies.Single(cookie => cookie.Name == "sesion");

        Assert.That(sesion.HttpOnly, Is.True);
        Assert.That(sesion.Secure, Is.True);
        Assert.That(sesion.SameSite, Is.EqualTo(SameSiteAttribute.Lax));
        // El JavaScript de la página no puede leer una cookie HttpOnly; la prueba sí, desde el contexto.
        Assert.That(await Page.EvaluateAsync<string>("() => document.cookie"), Does.Not.Contain("sesion="));
    }
    // #endregion
}
