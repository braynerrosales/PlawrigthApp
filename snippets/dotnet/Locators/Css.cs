namespace PlaywrightGuide.Snippets.Locators;

public class CssExamples : LocatorsTest
{
    [Test]
    public async Task CssEstable()
    {
        // #region example
        var loginForm = Page.Locator("#login-form");
        await loginForm.GetByLabel("Correo electrónico").FillAsync("qa@example.com");

        await Expect(Page.Locator("li.product-card")).ToHaveCountAsync(3);
        // #endregion
        await Expect(loginForm.GetByLabel("Correo electrónico")).ToHaveValueAsync("qa@example.com");
    }
}
