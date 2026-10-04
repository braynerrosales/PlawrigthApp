namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class ApiYUiExamples : PageTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = ApiDePractica.Url };

    // #region example
    [Test]
    public async Task UnPedidoCreadoPorApiApareceEnLaTabla()
    {
        var cliente = $"Cliente {DateTime.UtcNow.Ticks}";
        var response = await Page.APIRequest.PostAsync("/api/pedidos", new()
        {
            DataObject = new { cliente, producto = "Auriculares", cantidad = 1 },
        });
        Assert.That(response.Status, Is.EqualTo(201));

        await Page.GotoAsync("/");

        var fila = Page.GetByRole(AriaRole.Row).Filter(new() { HasText = cliente });
        await Expect(fila).ToContainTextAsync("Auriculares");
    }
    // #endregion
}
