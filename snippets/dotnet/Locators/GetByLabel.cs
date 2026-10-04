namespace PlaywrightGuide.Snippets.Locators;

public class GetByLabelExamples : LocatorsTest
{
    [Test]
    public async Task LocalizaCamposPorSuEtiqueta()
    {
        // #region example
        await Page.GetByLabel("Correo electrónico").FillAsync("qa@example.com");
        await Page.GetByLabel("Contraseña").FillAsync("clave-de-prueba");

        await Expect(Page.GetByLabel("Contraseña")).ToHaveValueAsync("clave-de-prueba");
        // #endregion
    }
}
