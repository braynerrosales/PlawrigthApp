namespace PlaywrightGuide.Snippets.Formularios;

public class SinVerificarEnvioExamples : FixtureTest
{
    protected override string Fixture => "formularios";

    [Test]
    public async Task EnviarElFormularioSinComprobarElResultado()
    {
        // #region example
        await Page.GetByLabel("Correo electrónico").FillAsync("ana@correo");
        await Page.GetByLabel("Fecha de entrega").FillAsync("2026-10-04");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Enviar solicitud" }).ClickAsync();
        // Fin de la prueba: pasa en verde, aunque la app rechazó el correo.
        // #endregion

        // Fuera del ejemplo: demuestra que el envío en realidad falló.
        await Expect(Page.GetByRole(AriaRole.Alert)).ToHaveTextAsync("Ingresa un correo válido");
    }
}
