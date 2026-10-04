namespace PlaywrightGuide.Snippets.Locators;

public class XpathExamples : LocatorsTest
{
    [Test]
    public async Task XpathPorContenido()
    {
        // #region example
        var keyboardPrice = Page.Locator("xpath=//li[h3[normalize-space()='Teclado mecánico']]/p");

        await Expect(keyboardPrice).ToHaveTextAsync("$45");
        // #endregion
    }
}
