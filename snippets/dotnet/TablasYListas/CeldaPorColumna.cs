namespace PlaywrightGuide.Snippets.TablasYListas;

public class CeldaPorColumnaExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    // #region example
    [Test]
    public async Task LeerElEstadoDeUnPedidoPorElNombreDeLaColumna()
    {
        var tabla = Page.GetByRole(AriaRole.Table, new() { Name = "Pedidos" });

        // El índice sale del encabezado: si se agrega o se mueve una columna, se recalcula.
        var encabezados = await tabla.GetByRole(AriaRole.Columnheader).AllTextContentsAsync();
        var columnaEstado = encabezados.ToList().IndexOf("Estado");

        // Has + Exact: la fila cuya celda es exactamente PED-1003.
        var fila = tabla.GetByRole(AriaRole.Row).Filter(new()
        {
            Has = Page.GetByRole(AriaRole.Cell, new() { Name = "PED-1003", Exact = true }),
        });
        await Expect(fila.GetByRole(AriaRole.Cell).Nth(columnaEstado)).ToHaveTextAsync("Pendiente");
    }
    // #endregion
}
