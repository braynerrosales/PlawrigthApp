namespace PlaywrightGuide.Snippets.Assertions;

public class TextoYValorExamples : FixtureTest
{
    protected override string Fixture => "locators";

    // #region example
    [Test]
    public async Task TextoValorYAtributos()
    {
        await Expect(Page.GetByTestId("cart-count")).ToHaveTextAsync("0");
        await Expect(Page.GetByText("Envío gratis")).ToContainTextAsync("mayores a $50");

        var buscador = Page.GetByPlaceholder("Buscar productos");
        await Expect(buscador).ToHaveAttributeAsync("type", "search");
        await buscador.FillAsync("teclado");
        await Expect(buscador).ToHaveValueAsync("teclado");
    }
    // #endregion
}
