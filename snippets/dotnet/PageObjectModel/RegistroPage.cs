namespace PlaywrightGuide.Snippets.PageObjectModel;

// Page object de la página de práctica fixtures/acciones.html. Lo usan las pruebas del módulo.
// #region example
public record DatosRegistro(string Nombre, string Correo, string Pais, string Plan);

public class RegistroPage
{
    private readonly IPage _page;

    public RegistroPage(IPage page)
    {
        _page = page;
        Nombre = page.GetByLabel("Nombre");
        Correo = page.GetByLabel("Correo electrónico");
        Pais = page.GetByLabel("País");
        Terminos = page.GetByLabel("Acepto los términos");
        CrearCuenta = page.GetByRole(AriaRole.Button, new() { Name = "Crear cuenta" });
        Confirmacion = page.GetByRole(AriaRole.Status);
    }

    public ILocator Nombre { get; }
    public ILocator Correo { get; }
    public ILocator Pais { get; }
    public ILocator Terminos { get; }
    public ILocator CrearCuenta { get; }
    public ILocator Confirmacion { get; }

    public ILocator Plan(string nombre) => _page.GetByRole(AriaRole.Radio, new() { Name = nombre });

    public async Task CompletarAsync(DatosRegistro datos)
    {
        await Nombre.FillAsync(datos.Nombre);
        await Correo.FillAsync(datos.Correo);
        await Pais.SelectOptionAsync(new SelectOptionValue { Label = datos.Pais });
        await Plan(datos.Plan).CheckAsync();
    }

    public async Task RegistrarAsync(DatosRegistro datos)
    {
        await CompletarAsync(datos);
        await Terminos.CheckAsync();
        await CrearCuenta.ClickAsync();
    }
}
// #endregion
