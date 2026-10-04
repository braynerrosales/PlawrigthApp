namespace PlaywrightGuide.Snippets.Formularios;

public class EnvioExitosoExamples : FixtureTest
{
    protected override string Fixture => "formularios";

    // #region example
    [Test]
    public async Task EnviarConEnterYComprobarElResultado()
    {
        var correo = Page.GetByLabel("Correo electrónico");
        var fecha = Page.GetByLabel("Fecha de entrega");

        await fecha.FillAsync("2026-10-04");
        await correo.FillAsync("ana@example.com");
        // Enter dentro de un campo envía el formulario, como lo haría una persona.
        await correo.PressAsync("Enter");

        // El resultado visible y el estado posterior del formulario.
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Solicitud PED-1042 enviada");
        await Expect(Page.GetByRole(AriaRole.Alert)).ToHaveCountAsync(0);
        await Expect(correo).ToBeEmptyAsync();
        await Expect(fecha).ToBeEmptyAsync();
    }
    // #endregion
}
