namespace PlaywrightGuide.Snippets.PageObjectModel;

public class LocatorsDuplicadosExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task RegistroDeUnaCuentaGratis()
    {
        await Page.GetByLabel("Nombre").FillAsync("Ana Pérez");
        await Page.GetByLabel("Correo electrónico").FillAsync("ana@example.com");
        await Page.GetByLabel("Acepto los términos").CheckAsync();
        await Page.GetByRole(AriaRole.Button, new() { Name = "Crear cuenta" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Cuenta Gratis creada para Ana Pérez");
    }

    [Test]
    public async Task SinAceptarLosTerminosNoSePuedeCrearLaCuenta()
    {
        await Page.GetByLabel("Nombre").FillAsync("Ana Pérez");
        await Page.GetByLabel("Correo electrónico").FillAsync("ana@example.com");

        await Expect(Page.GetByRole(AriaRole.Button, new() { Name = "Crear cuenta" })).ToBeDisabledAsync();
    }
    // #endregion
}
