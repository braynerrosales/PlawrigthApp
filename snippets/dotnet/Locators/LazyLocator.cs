namespace PlaywrightGuide.Snippets.Locators;

public class LazyLocatorExamples : LocatorsTest
{
    [Test]
    public async Task LocatorSeResuelveDeNuevoEnCadaUso()
    {
        // #region example
        // Describe CÓMO encontrar el elemento; todavía no busca nada en la página.
        var status = Page.GetByRole(AriaRole.Status);
        await Expect(status).ToBeEmptyAsync();

        await Page.GetByLabel("Correo electrónico").FillAsync("qa@example.com");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Iniciar sesión" }).ClickAsync();

        // La app reemplazó el nodo; el mismo Locator encuentra el nuevo.
        await Expect(status).ToHaveTextAsync("Bienvenido, qa@example.com");
        // #endregion
    }
}
