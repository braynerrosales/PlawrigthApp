using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.TablasYListas;

public class OrdenarExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    // #region example
    [Test]
    public async Task OrdenarPorClienteYComprobarElOrden()
    {
        var tabla = Page.GetByRole(AriaRole.Table, new() { Name = "Pedidos" });
        // Solo las filas con celdas de datos: la fila del encabezado tiene columnheader, no cell.
        var filas = tabla.GetByRole(AriaRole.Row).Filter(new() { Has = Page.GetByRole(AriaRole.Cell) });

        await tabla.GetByRole(AriaRole.Button, new() { Name = "Cliente" }).ClickAsync();

        await Expect(tabla.GetByRole(AriaRole.Columnheader, new() { Name = "Cliente" }))
            .ToHaveAttributeAsync("aria-sort", "ascending");
        // Un arreglo comprueba la cantidad de filas y el texto de cada una, en orden.
        await Expect(filas).ToHaveTextAsync(new[]
        {
            new Regex("Ana Torres"), new Regex("Bruno Ríos"), new Regex("Carla Gómez"),
            new Regex("Diego Ruiz"), new Regex("Elena Mora"),
        });
    }
    // #endregion
}
