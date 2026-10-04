namespace PlaywrightGuide.Snippets.Locators;

public class GetByTestIdExamples : LocatorsTest
{
    [Test]
    public async Task LocalizaPorAtributoDePruebas()
    {
        // #region example
        await Expect(Page.GetByTestId("cart-count")).ToHaveTextAsync("0");
        await Expect(Page.GetByTestId("product-list").GetByRole(AriaRole.Listitem)).ToHaveCountAsync(3);
        // #endregion
    }
}
