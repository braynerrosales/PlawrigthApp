namespace PlaywrightGuide.Snippets.Assertions;

public class ListasExamples : FixtureTest
{
    protected override string Fixture => "locators";

    // #region example
    [Test]
    public async Task CantidadYContenidoDeUnaLista()
    {
        var productos = Page.GetByTestId("product-list").GetByRole(AriaRole.Listitem);

        await Expect(productos).ToHaveCountAsync(3);
        await Expect(productos.GetByRole(AriaRole.Heading)).ToHaveTextAsync(new[] { "Teclado mecánico", "Mouse inalámbrico", "Monitor 4K" });
    }
    // #endregion
}
