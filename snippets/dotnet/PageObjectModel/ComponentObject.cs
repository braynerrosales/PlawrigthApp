namespace PlaywrightGuide.Snippets.PageObjectModel;

// #region example
public class TarjetaProducto
{
    public TarjetaProducto(ILocator raiz)
    {
        Raiz = raiz;
        Nombre = raiz.GetByRole(AriaRole.Heading);
        Agregar = raiz.GetByRole(AriaRole.Button, new() { Name = "Agregar al carrito" });
    }

    public ILocator Raiz { get; }
    public ILocator Nombre { get; }
    public ILocator Agregar { get; }

    public Task AgregarAlCarritoAsync() => Agregar.ClickAsync();
}

public class TiendaPage
{
    private readonly IPage _page;

    public TiendaPage(IPage page)
    {
        _page = page;
        ContadorCarrito = page.GetByTestId("cart-count");
    }

    public ILocator ContadorCarrito { get; }

    public TarjetaProducto Producto(string nombre) =>
        new(_page.GetByRole(AriaRole.Listitem).Filter(new()
        {
            Has = _page.GetByRole(AriaRole.Heading, new() { Name = nombre }),
        }));
}

public class ComponentObjectExamples : FixtureTest
{
    protected override string Fixture => "locators";

    [Test]
    public async Task AgregarUnProductoDesdeSuTarjeta()
    {
        var tienda = new TiendaPage(Page);

        await tienda.Producto("Mouse inalámbrico").AgregarAlCarritoAsync();

        await Expect(tienda.ContadorCarrito).ToHaveTextAsync("1");
        await Expect(tienda.Producto("Monitor 4K").Agregar).ToBeDisabledAsync();
    }
}
// #endregion
