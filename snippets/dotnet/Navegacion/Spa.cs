using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.Navegacion;

public class SpaExamples : TiendaTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = Tienda };

    // #region example
    [Test]
    public async Task NavegarDentroDeUnaSpa()
    {
        await Page.GotoAsync("/cuenta");
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Perfil" })).ToBeVisibleAsync();

        // La app cambia la URL con history.pushState: no se carga un documento nuevo.
        await Page.GetByRole(AriaRole.Link, new() { Name = "Pedidos" }).ClickAsync();
        await Expect(Page).ToHaveURLAsync(new Regex(@"/cuenta/pedidos$"));
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Pedidos" })).ToBeVisibleAsync();

        // Atrás también funciona: la app escucha popstate y vuelve a mostrar Perfil.
        await Page.GoBackAsync();
        await Expect(Page).ToHaveURLAsync(new Regex(@"/cuenta$"));
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Perfil" })).ToBeVisibleAsync();
    }
    // #endregion
}
