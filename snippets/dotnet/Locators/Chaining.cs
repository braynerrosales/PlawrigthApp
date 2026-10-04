namespace PlaywrightGuide.Snippets.Locators;

public class ChainingExamples : LocatorsTest
{
    [Test]
    public async Task EncadenaLocators()
    {
        // #region example
        var productList = Page.GetByTestId("product-list");
        var mouseCard = productList.GetByRole(AriaRole.Listitem).Filter(new() { HasText = "Mouse inalámbrico" });

        await mouseCard.GetByRole(AriaRole.Button, new() { Name = "Agregar al carrito" }).ClickAsync();

        await Expect(Page.GetByTestId("cart-count")).ToHaveTextAsync("1");
        // #endregion
    }
}
