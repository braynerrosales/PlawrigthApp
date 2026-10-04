namespace PlaywrightGuide.Snippets.TablasYListas;

public class ConteoInmediatoExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    [Test]
    public async Task ContarUnaVezYRecorrerConIndicesMientrasLaListaCarga()
    {
        var movimientos = Page.GetByRole(AriaRole.List, new() { Name = "Últimos movimientos" })
            .GetByRole(AriaRole.Listitem);

        // #region example
        // CountAsync no espera: la lista todavía está cargando y devuelve 0.
        var total = await movimientos.CountAsync();
        for (var i = 0; i < total; i++)
        {
            await Expect(movimientos.Nth(i)).ToContainTextAsync("PED-");
        }
        // Fin de la prueba: pasa en verde sin haber comprobado ningún movimiento.
        // #endregion

        // Fuera del ejemplo: demuestra que el bucle no recorrió nada y que la lista sí tenía datos.
        Assert.That(total, Is.EqualTo(0));
        await Expect(movimientos).ToHaveCountAsync(4);
    }
}
