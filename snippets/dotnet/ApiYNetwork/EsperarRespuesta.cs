namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class EsperarRespuestaExamples : PageTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = ApiDePractica.Url };

    // #region example
    [Test]
    public async Task CrearUnPedidoDesdeElFormulario()
    {
        await Page.GotoAsync("/");
        await Page.GetByLabel("Cliente").FillAsync("Prueba UI");
        await Page.GetByLabel("Producto").FillAsync("Silla");
        await Page.GetByLabel("Cantidad").FillAsync("4");

        var response = await Page.RunAndWaitForResponseAsync(async () =>
        {
            await Page.GetByRole(AriaRole.Button, new() { Name = "Crear pedido" }).ClickAsync();
        }, r => r.Url.EndsWith("/api/pedidos") && r.Request.Method == "POST");

        Assert.That(response.Status, Is.EqualTo(201));
        var id = (await response.JsonAsync())!.Value.GetProperty("id").GetInt32();
        await Expect(Page.GetByRole(AriaRole.Alert)).ToHaveTextAsync($"Pedido #{id} creado");
    }
    // #endregion
}
