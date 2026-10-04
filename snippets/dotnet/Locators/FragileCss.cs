namespace PlaywrightGuide.Snippets.Locators;

public class FragileCssExamples : LocatorsTest
{
    [Test]
    public async Task CssPosicional()
    {
        // #region example
        await Page.Locator("body > div:nth-child(2) > div:nth-child(4) > button").ClickAsync();
        // #endregion
        await Expect(Page.Locator("#settings-state")).ToHaveTextAsync("Cambios guardados");
    }
}
