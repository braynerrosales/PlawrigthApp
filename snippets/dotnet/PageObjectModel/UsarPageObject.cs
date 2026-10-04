namespace PlaywrightGuide.Snippets.PageObjectModel;

public class UsarPageObjectExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    private static readonly DatosRegistro Ana = new("Ana Pérez", "ana@example.com", "Colombia", "Pro");

    [Test]
    public async Task RegistroDeUnaCuentaPro()
    {
        var registro = new RegistroPage(Page);

        await registro.RegistrarAsync(Ana);

        await Expect(registro.Confirmacion).ToHaveTextAsync("Cuenta Pro creada para Ana Pérez");
    }

    [Test]
    public async Task SinAceptarLosTerminosNoSePuedeCrearLaCuenta()
    {
        var registro = new RegistroPage(Page);

        await registro.CompletarAsync(Ana);

        await Expect(registro.CrearCuenta).ToBeDisabledAsync();
    }
    // #endregion
}
