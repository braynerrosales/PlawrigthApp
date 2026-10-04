namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class EsperaFijaExamples : PageTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = ApiDePractica.Url };

    // #region example
    [Test]
    public async Task CrearUnPedidoEsperandoUnTiempoFijo()
    {
        await Page.GotoAsync("/");
        await Page.GetByLabel("Cliente").FillAsync("Prueba UI");
        await Page.GetByLabel("Producto").FillAsync("Silla");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Crear pedido" }).ClickAsync();

        // «Dos segundos deberían bastar»: sobra casi siempre y, el día que la API tarda más, falla.
        await Page.WaitForTimeoutAsync(2_000);
        var mensaje = await Page.GetByRole(AriaRole.Alert).TextContentAsync();
        Assert.That(mensaje, Does.Contain("creado"));
    }
    // #endregion
}
