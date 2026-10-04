namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class SimularErrorExamples : PageTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = ApiDePractica.Url };

    // #region example
    [Test]
    public async Task AvisarCuandoLaApiFalla()
    {
        await Page.RouteAsync("**/api/pedidos", async route => await route.FulfillAsync(new()
        {
            Status = 500,
            Json = new { errores = new[] { "error interno" } },
        }));

        await Page.GotoAsync("/");

        await Expect(Page.GetByRole(AriaRole.Status))
            .ToHaveTextAsync("No se pudieron cargar los pedidos. Inténtalo de nuevo más tarde.");
        await Expect(Page.GetByRole(AriaRole.Table)).ToBeHiddenAsync();
    }
    // #endregion
}
