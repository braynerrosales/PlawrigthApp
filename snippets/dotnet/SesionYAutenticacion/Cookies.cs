using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.SesionYAutenticacion;

public class CookiesExamples : PortalTest
{
    // #region example
    [Test]
    public async Task EntrarConUnaCookieYPerderLaSesionAlBorrarla()
    {
        // Token ficticio de prueba: en un proyecto real lo entrega una API o un login previo.
        await Context.AddCookiesAsync(new[]
        {
            new Cookie { Name = "sesion", Value = "token-ficticio-ana", Url = Portal, HttpOnly = true, Secure = true },
        });

        await Page.GotoAsync("/panel");
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Hola, Ana" })).ToBeVisibleAsync();

        await Context.ClearCookiesAsync(new() { Name = "sesion" });
        Assert.That(await Context.CookiesAsync(Portal), Is.Empty);

        // Sin cookie, el panel manda al login.
        await Page.GotoAsync("/panel");
        await Expect(Page).ToHaveURLAsync(new Regex(@"/login$"));
    }
    // #endregion
}
