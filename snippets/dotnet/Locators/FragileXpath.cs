namespace PlaywrightGuide.Snippets.Locators;

public class FragileXpathExamples : LocatorsTest
{
    [Test]
    public async Task XpathAbsoluto()
    {
        // #region example
        await Page.Locator("xpath=/html/body/div/div[4]/button").ClickAsync();
        // #endregion
        await Expect(Page.Locator("#settings-state")).ToHaveTextAsync("Cambios guardados");
    }
}
