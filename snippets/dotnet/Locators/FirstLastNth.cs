namespace PlaywrightGuide.Snippets.Locators;

public class FirstLastNthExamples : LocatorsTest
{
    [Test]
    public async Task EligePorPosicion()
    {
        // #region example
        var addButtons = Page.GetByRole(AriaRole.Button, new() { Name = "Agregar al carrito" });
        await Expect(addButtons).ToHaveCountAsync(3);

        await addButtons.First.ClickAsync(); // índice 0 (propiedad, no método)
        await addButtons.Nth(1).ClickAsync(); // índice 1: Nth empieza en cero
        await Expect(addButtons.Last).ToBeDisabledAsync();
        // #endregion
        await Expect(Page.GetByTestId("cart-count")).ToHaveTextAsync("2");
    }
}
