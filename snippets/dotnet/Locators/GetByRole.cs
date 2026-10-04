namespace PlaywrightGuide.Snippets.Locators;

public class GetByRoleExamples : LocatorsTest
{
    [Test]
    public async Task LocalizaPorRolYNombreAccesible()
    {
        // #region example
        await Page.GetByRole(AriaRole.Textbox, new() { Name = "Correo electrónico" }).FillAsync("qa@example.com");
        await Page.GetByRole(AriaRole.Checkbox, new() { Name = "Recordarme" }).CheckAsync();
        await Page.GetByRole(AriaRole.Button, new() { Name = "Iniciar sesión" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Heading, new() { Name = "Productos", Level = 2 })).ToBeVisibleAsync();
        // #endregion
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Bienvenido, qa@example.com");
    }
}
