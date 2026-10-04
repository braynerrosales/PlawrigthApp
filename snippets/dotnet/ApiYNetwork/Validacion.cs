namespace PlaywrightGuide.Snippets.ApiYNetwork;

public class ValidacionExamples : PlaywrightTest
{
    private IAPIRequestContext Request = null!;

    [SetUp]
    public async Task CrearContextoDeApi() =>
        Request = await Playwright.APIRequest.NewContextAsync(new() { BaseURL = ApiDePractica.Url });

    [TearDown]
    public async Task CerrarContextoDeApi() => await Request.DisposeAsync();

    // #region example
    [Test]
    public async Task RechazarUnPedidoSinCantidadValida()
    {
        var response = await Request.PostAsync("/api/pedidos", new()
        {
            DataObject = new { cliente = "Prueba API", producto = "Webcam", cantidad = 0 },
        });

        await Expect(response).Not.ToBeOKAsync();
        Assert.That(response.Status, Is.EqualTo(400));
        var errores = (await response.JsonAsync())!.Value.GetProperty("errores");
        Assert.That(errores.GetArrayLength(), Is.EqualTo(1));
        Assert.That(errores[0].GetString(), Is.EqualTo("cantidad debe ser un entero mayor que 0"));
    }
    // #endregion
}
