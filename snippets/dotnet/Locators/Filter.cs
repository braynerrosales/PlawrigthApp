namespace PlaywrightGuide.Snippets.Locators;

public class FilterExamples : LocatorsTest
{
    [Test]
    public async Task FiltraPorTextoOContenido()
    {
        // #region example
        var keyboard = Page.GetByRole(AriaRole.Listitem).Filter(new() { HasText = "Teclado mecánico" });
        await keyboard.GetByRole(AriaRole.Button, new() { Name = "Agregar al carrito" }).ClickAsync();
        await Expect(Page.GetByTestId("cart-count")).ToHaveTextAsync("1");

        // Has: el item debe contener otro Locator.
        var soldOut = Page.GetByRole(AriaRole.Listitem).Filter(new()
        {
            Has = Page.GetByText("Agotado", new() { Exact = true }),
        });
        await Expect(soldOut.GetByRole(AriaRole.Heading)).ToHaveTextAsync("Monitor 4K");
        // #endregion
    }
}
