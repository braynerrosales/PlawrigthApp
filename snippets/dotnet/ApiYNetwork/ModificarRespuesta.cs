using System.Text.Json.Nodes;

namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class ModificarRespuestaExamples : PageTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = ApiDePractica.Url };

    // #region example
    [Test]
    public async Task MostrarLosPedidosComoEnviados()
    {
        await Page.RouteAsync("**/api/pedidos", async route =>
        {
            var response = await route.FetchAsync();
            var pedidos = JsonNode.Parse(await response.TextAsync())!.AsArray();
            foreach (var pedido in pedidos) pedido!["estado"] = "enviado";
            await route.FulfillAsync(new() { Response = response, Body = pedidos.ToJsonString() });
        });

        await Page.GotoAsync("/");

        await Expect(Page.GetByRole(AriaRole.Row).Filter(new() { HasText = "Ana Torres" }))
            .ToContainTextAsync("enviado");
    }
    // #endregion
}
