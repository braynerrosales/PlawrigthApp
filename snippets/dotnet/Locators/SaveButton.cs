namespace PlaywrightGuide.Snippets.Locators;

public class SaveButtonExamples : LocatorsTest
{
    [Test]
    public async Task BotonPorRolYNombre()
    {
        // #region example
        await Page.GetByRole(AriaRole.Button, new() { Name = "Guardar" }).ClickAsync();
        // #endregion
        await Expect(Page.GetByText("Cambios guardados")).ToBeVisibleAsync();
    }
}
