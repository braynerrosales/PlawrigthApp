namespace PlaywrightGuide.Snippets.Acciones;

public class JsValueExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task AsignarElValorConJavaScriptNoDisparaEventos()
    {
        await Page.GetByLabel("Nombre").EvaluateAsync("el => el.value = 'Ana Pérez'");
        await Page.GetByLabel("Correo electrónico").EvaluateAsync("el => el.value = 'ana@example.com'");
        await Page.GetByLabel("Acepto los términos").CheckAsync();

        // La app nunca recibió eventos input en los campos de texto: el botón sigue deshabilitado.
        await Expect(Page.GetByRole(AriaRole.Button, new() { Name = "Crear cuenta" })).ToBeDisabledAsync();
    }
    // #endregion
}
