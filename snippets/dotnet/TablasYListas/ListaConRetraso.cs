namespace PlaywrightGuide.Snippets.TablasYListas;

public class ListaConRetrasoExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    // #region example
    [Test]
    public async Task EsperarUnaListaQueSeCargaConRetraso()
    {
        var movimientos = Page.GetByRole(AriaRole.List, new() { Name = "Últimos movimientos" })
            .GetByRole(AriaRole.Listitem);

        // Reintenta hasta que la lista tiene exactamente estos elementos, en este orden.
        await Expect(movimientos).ToHaveTextAsync(new[]
        {
            "PED-1003 pagado", "PED-1007 enviado", "PED-1001 entregado", "PED-1005 cancelado",
        });

        // Leer los textos solo cuando necesitas los datos, y después de la aserción: AllTextContentsAsync no espera.
        var textos = await movimientos.AllTextContentsAsync();
        Assert.That(textos.Where(texto => texto.EndsWith("cancelado")), Is.EqualTo(new[] { "PED-1005 cancelado" }));
    }
    // #endregion
}
