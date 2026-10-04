namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class MockRespuestaExamples : PageTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = ApiDePractica.Url };

    // #region example
    [Test]
    public async Task MostrarLosPedidosQueDevuelveLaApiSimulada()
    {
        await Page.RouteAsync("**/api/pedidos", async route => await route.FulfillAsync(new()
        {
            Json = new[]
            {
                new { id = 101, cliente = "Cliente simulado", producto = "Mesa", cantidad = 1, estado = "pendiente" },
                new { id = 102, cliente = "Otro cliente", producto = "Lámpara", cantidad = 5, estado = "enviado" },
            },
        }));

        await Page.GotoAsync("/");

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("2 pedidos");
        await Expect(Page.GetByRole(AriaRole.Row).Filter(new() { HasText = "Cliente simulado" }))
            .ToContainTextAsync("Mesa");
    }
    // #endregion
}
