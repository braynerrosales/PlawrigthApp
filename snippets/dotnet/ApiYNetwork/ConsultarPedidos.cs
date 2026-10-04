namespace PlaywrightGuide.Snippets.ApiYNetwork;

// #region example
public class ConsultarPedidosExamples : PlaywrightTest
{
    private IAPIRequestContext Request = null!;

    [SetUp]
    public async Task CrearContextoDeApi()
    {
        Request = await Playwright.APIRequest.NewContextAsync(new() { BaseURL = ApiDePractica.Url });
    }

    [TearDown]
    public async Task CerrarContextoDeApi()
    {
        await Request.DisposeAsync();
    }

    [Test]
    public async Task ConsultarUnPedidoPorLaApi()
    {
        var response = await Request.GetAsync("/api/pedidos/1");

        await Expect(response).ToBeOKAsync();
        var pedido = (await response.JsonAsync())!.Value;
        Assert.That(pedido.GetProperty("cliente").GetString(), Is.EqualTo("Ana Torres"));
        Assert.That(pedido.GetProperty("producto").GetString(), Is.EqualTo("Teclado"));
    }
}
// #endregion
