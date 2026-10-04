namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class CrearYEliminarExamples : PlaywrightTest
{
    private IAPIRequestContext Request = null!;

    [SetUp]
    public async Task CrearContextoDeApi() =>
        Request = await Playwright.APIRequest.NewContextAsync(new() { BaseURL = ApiDePractica.Url });

    [TearDown]
    public async Task CerrarContextoDeApi() => await Request.DisposeAsync();

    // #region example
    [Test]
    public async Task CrearLeerYEliminarUnPedido()
    {
        var creado = await Request.PostAsync("/api/pedidos", new()
        {
            DataObject = new { cliente = "Prueba API", producto = "Webcam", cantidad = 2 },
        });
        Assert.That(creado.Status, Is.EqualTo(201));
        var id = (await creado.JsonAsync())!.Value.GetProperty("id").GetInt32();

        var leido = await Request.GetAsync($"/api/pedidos/{id}");
        await Expect(leido).ToBeOKAsync();
        var pedido = (await leido.JsonAsync())!.Value;
        Assert.That(pedido.GetProperty("cliente").GetString(), Is.EqualTo("Prueba API"));
        Assert.That(pedido.GetProperty("cantidad").GetInt32(), Is.EqualTo(2));
        Assert.That(pedido.GetProperty("estado").GetString(), Is.EqualTo("pendiente"));

        var eliminado = await Request.DeleteAsync($"/api/pedidos/{id}");
        Assert.That(eliminado.Status, Is.EqualTo(204));
        Assert.That((await Request.GetAsync($"/api/pedidos/{id}")).Status, Is.EqualTo(404));
    }
    // #endregion
}
