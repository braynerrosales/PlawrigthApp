namespace PlaywrightGuide.Snippets;

/// <summary>
/// Sirve el sitio de práctica <c>snippets/fixtures/tienda</c> en <c>https://tienda.test</c>
/// interceptando las peticiones del contexto; nunca sale a la red. Igual que <c>serveTienda</c> en TypeScript.
/// </summary>
public abstract class TiendaTest : PageTest
{
    protected const string Tienda = "https://tienda.test";

    private static readonly Dictionary<string, string> Pages = new()
    {
        ["/"] = "index",
        ["/productos"] = "productos",
        ["/login"] = "login",
        ["/cuenta"] = "cuenta",
        ["/cuenta/pedidos"] = "cuenta",
    };

    [SetUp]
    public async Task ServeTienda() =>
        await Context.RouteAsync($"{Tienda}/**", async route =>
        {
            var found = Pages.TryGetValue(new Uri(route.Request.Url).AbsolutePath, out var page);
            await route.FulfillAsync(new()
            {
                Status = found ? 200 : 404,
                ContentType = "text/html; charset=utf-8",
                Body = await File.ReadAllTextAsync(Path.Combine(
                    AppContext.BaseDirectory, "fixtures", "tienda", $"{page ?? "no-encontrada"}.html")),
            });
        });
}
