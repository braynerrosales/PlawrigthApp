namespace PlaywrightGuide.Snippets.Migracion;

// #region example
public class PedidosConPlaywright : PageTest
{
    [Test]
    public async Task ExportarPedidos()
    {
        await Page.GotoAsync(Fixtures.Url("pedidos"));
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        await Page.GetByRole(AriaRole.Button, new() { Name = "Exportar" }).ClickAsync();

        await Expect(Page.GetByRole(AriaRole.Status)).ToHaveTextAsync("Exportación lista");
        await Expect(Page.GetByRole(AriaRole.Listitem)).ToHaveCountAsync(3);
    }
}
// #endregion
