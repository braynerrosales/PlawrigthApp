namespace PlaywrightGuide.Snippets.PageObjectModel;

// #region example
public abstract class RegistroTest : FixtureTest
{
    protected override string Fixture => "acciones";

    protected RegistroPage Registro { get; private set; } = null!;

    // NUnit ejecuta primero los [SetUp] de las clases base: Page ya existe aquí.
    [SetUp]
    public void CrearPageObject() => Registro = new RegistroPage(Page);
}

public class SetupExamples : RegistroTest
{
    [Test]
    public async Task RegistroDeUnaCuentaGratis()
    {
        await Registro.RegistrarAsync(new DatosRegistro("Luis Gómez", "luis@example.com", "Chile", "Gratis"));

        await Expect(Registro.Confirmacion).ToHaveTextAsync("Cuenta Gratis creada para Luis Gómez");
    }
}
// #endregion
