namespace PlaywrightGuide.Snippets.Acciones;

public class QaCaseExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task RegistroDeUnaCuentaPro()
    {
        await Page.GetByLabel("Nombre").FillAsync("Ana Pérez");
        await Page.GetByLabel("Correo electrónico").FillAsync("ana@example.com");
        await Page.GetByLabel("País").SelectOptionAsync(new SelectOptionValue { Label = "Colombia" });
        await Page.GetByRole(AriaRole.Radio, new() { Name = "Pro" }).CheckAsync();
        await Page.GetByLabel("Acepto los términos").CheckAsync();

        await Page.GetByRole(AriaRole.Button, new() { Name = "Crear cuenta" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Cuenta Pro creada para Ana Pérez");
    }
    // #endregion
}
