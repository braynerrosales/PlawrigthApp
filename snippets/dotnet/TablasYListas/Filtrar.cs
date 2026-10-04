using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.TablasYListas;

public class FiltrarExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    // #region example
    [Test]
    public async Task FiltrarYComprobarQueSoloQuedanLosPedidosEsperados()
    {
        var filas = Page.GetByRole(AriaRole.Table, new() { Name = "Pedidos" })
            .GetByRole(AriaRole.Row).Filter(new() { Has = Page.GetByRole(AriaRole.Cell) });

        await Page.GetByLabel("Filtrar pedidos").FillAsync("Pendiente");

        // La app aplica el filtro 300 ms después: las aserciones reintentan hasta verlo.
        await Expect(filas).ToHaveCountAsync(3);
        await Expect(filas).ToHaveTextAsync(new[]
        {
            new Regex("PED-1003"), new Regex("PED-1006"), new Regex("PED-1009"),
        });
    }
    // #endregion
}
