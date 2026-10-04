namespace PlaywrightGuide.Snippets.Formularios;

public class ValidacionExamples : FixtureTest
{
    protected override string Fixture => "formularios";

    // #region example
    [Test]
    public async Task UnCorreoInvalidoMuestraElErrorDeLaApp()
    {
        var correo = Page.GetByLabel("Correo electrónico");

        await correo.FillAsync("ana@correo");
        await Page.GetByLabel("Fecha de entrega").FillAsync("2026-10-04");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Enviar solicitud" }).ClickAsync();

        // El mensaje es de la app, no del navegador: es estable y se puede comprobar.
        await Expect(Page.GetByRole(AriaRole.Alert)).ToHaveTextAsync("Ingresa un correo válido");
        await Expect(correo).ToHaveAttributeAsync("aria-invalid", "true");
        await Expect(Page.GetByRole(AriaRole.Status)).ToBeEmptyAsync();
    }
    // #endregion
}
