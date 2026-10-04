namespace PlaywrightGuide.Snippets.Navegacion;

public class RespuestaExamples : TiendaTest
{
    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = Tienda };

    // #region example
    [Test]
    public async Task UnaRutaInexistenteResponde404SinLanzarExcepcion()
    {
        // GotoAsync devuelve la respuesta del documento principal y no falla por un 404 o un 500.
        var response = await Page.GotoAsync("/no-existe");

        Assert.That(response, Is.Not.Null);
        Assert.That(response!.Status, Is.EqualTo(404));
        Assert.That(response.Ok, Is.False);
        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Página no encontrada" })).ToBeVisibleAsync();
    }
    // #endregion
}
