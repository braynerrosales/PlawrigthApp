using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.TablasYListas;

public class PaginacionExamples : FixtureTest
{
    protected override string Fixture => "tablas-y-listas";

    // #region example
    [Test]
    public async Task BuscarUnPedidoPaginaPorPagina()
    {
        var pedido = Page.GetByRole(AriaRole.Row).Filter(new() { HasText = "PED-1011" });
        var siguiente = Page.GetByRole(AriaRole.Button, new() { Name = "Siguiente" });
        var indicador = Page.GetByRole(AriaRole.Navigation, new() { Name = "Paginación" })
            .GetByRole(AriaRole.Status);

        // Bucle acotado: si el pedido no aparece en 10 páginas, la prueba falla con un motivo claro.
        for (var pagina = 1; !await pedido.IsVisibleAsync(); pagina++)
        {
            if (pagina == 10) Assert.Fail("PED-1011 no aparece en las primeras 10 páginas");
            await siguiente.ClickAsync();
            // IsVisibleAsync no espera: antes de mirar otra vez, se espera a que cargue la página nueva.
            await Expect(indicador).ToHaveTextAsync(new Regex($"^Página {pagina + 1} de"));
        }

        await Expect(pedido).ToContainTextAsync("Karen Sosa");
    }
    // #endregion
}
