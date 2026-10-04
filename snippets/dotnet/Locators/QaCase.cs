namespace PlaywrightGuide.Snippets.Locators;

public class QaCaseExamples : LocatorsTest
{
    // #region example
    [Test]
    public async Task ClienteAgregaProductoDisponibleAlCarrito()
    {
        await Page.GetByLabel("Correo electrónico").FillAsync("qa@example.com");
        await Page.GetByLabel("Contraseña").FillAsync("clave-de-prueba");
        await Page.GetByRole(AriaRole.Button, new() { Name = "Iniciar sesión" }).ClickAsync();
        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Bienvenido, qa@example.com");

        var product = Page.GetByRole(AriaRole.Listitem).Filter(new() { HasText = "Mouse inalámbrico" }); // [!mark]
        await product.GetByRole(AriaRole.Button, new() { Name = "Agregar al carrito" }).ClickAsync(); // [!mark]

        await Expect(Page.GetByTestId("cart-count")).ToHaveTextAsync("1");
    }
    // #endregion
}
