using System.Text.Json;
using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.SesionYAutenticacion;

/// <summary>
/// Sitio de práctica <c>snippets/fixtures/sesion</c> (Portal QA) servido en <c>https://portal.test</c>
/// interceptando las peticiones del contexto; nunca sale a la red. Igual que <c>portal.ts</c> en TypeScript.
/// Las cuentas y sus claves son ficticias, solo para estos ejemplos.
/// </summary>
public static class PortalSite
{
    public const string Url = "https://portal.test";

    private sealed record Cuenta(string Clave, string Nombre, string Rol, string Token);

    private static string ReadFixture(string name) =>
        File.ReadAllText(Path.Combine(AppContext.BaseDirectory, "fixtures", "sesion", name));

    private static readonly Dictionary<string, Cuenta> Cuentas =
        JsonSerializer.Deserialize<Dictionary<string, Cuenta>>(
            ReadFixture("cuentas.json"), new JsonSerializerOptions(JsonSerializerDefaults.Web))!;

    /// <summary>Simula el servidor: login con cookie de sesión y un panel privado que manda a /login sin ella.</summary>
    public static Task ServeAsync(IBrowserContext context) =>
        context.RouteAsync($"{Url}/**", async route =>
        {
            var request = route.Request;
            var path = new Uri(request.Url).AbsolutePath;

            if (path == "/api/login" && request.Method == "POST")
            {
                using var datos = JsonDocument.Parse(request.PostData ?? "{}");
                var usuario = datos.RootElement.GetProperty("usuario").GetString() ?? "";
                var clave = datos.RootElement.GetProperty("clave").GetString();
                if (!Cuentas.TryGetValue(usuario, out var cuenta) || cuenta.Clave != clave)
                {
                    await route.FulfillAsync(new() { Status = 401, Json = new { error = "credenciales" } });
                    return;
                }
                await route.FulfillAsync(new()
                {
                    Status = 200,
                    Headers = new Dictionary<string, string>
                    {
                        ["Set-Cookie"] = $"sesion={cuenta.Token}; Path=/; HttpOnly; Secure; SameSite=Lax",
                    },
                    Json = new { nombre = cuenta.Nombre },
                });
                return;
            }

            if (path == "/login")
            {
                await route.FulfillAsync(new() { ContentType = "text/html; charset=utf-8", Body = ReadFixture("login.html") });
                return;
            }

            if (path == "/panel")
            {
                var cookie = await request.HeaderValueAsync("cookie") ?? "";
                var token = Regex.Match(cookie, @"(?:^|;\s*)sesion=([^;]+)").Groups[1].Value;
                var cuenta = Cuentas.Values.FirstOrDefault(c => c.Token == token);
                if (cuenta is null)
                {
                    await route.FulfillAsync(new()
                    {
                        Status = 401,
                        ContentType = "text/html; charset=utf-8",
                        Body = ReadFixture("sin-sesion.html"),
                    });
                    return;
                }
                var html = ReadFixture("panel.html").Replace("{{nombre}}", cuenta.Nombre).Replace("{{rol}}", cuenta.Rol);
                if (cuenta.Rol != "admin")
                {
                    html = Regex.Replace(html, @"<!-- admin -->[\s\S]*<!-- /admin -->", "");
                }
                await route.FulfillAsync(new() { ContentType = "text/html; charset=utf-8", Body = html });
                return;
            }

            await route.FulfillAsync(new() { Status = 404, ContentType = "text/plain; charset=utf-8", Body = "No encontrada" });
        });

    /// <summary>Inicia sesión por la interfaz en un contexto nuevo y guarda su estado en <paramref name="path"/>.</summary>
    public static async Task SaveSessionAsync(IBrowser browser, string usuario, string path)
    {
        var context = await browser.NewContextAsync(new() { BaseURL = Url });
        await ServeAsync(context);
        var page = await context.NewPageAsync();
        await page.GotoAsync("/login");
        await page.GetByLabel("Usuario").FillAsync(usuario);
        await page.GetByLabel("Contraseña").FillAsync(Cuentas[usuario].Clave);
        await page.GetByRole(AriaRole.Button, new() { Name = "Ingresar" }).ClickAsync();
        await page.WaitForURLAsync("**/panel");
        await context.StorageStateAsync(new() { Path = path });
        await context.CloseAsync();
    }
}

/// <summary>Prueba con una página cuyo contexto ya sirve Portal QA en <c>BaseURL</c>.</summary>
public abstract class PortalTest : PageTest
{
    protected const string Portal = PortalSite.Url;

    public override BrowserNewContextOptions ContextOptions() => new() { BaseURL = Portal };

    [SetUp]
    public Task ServePortal() => PortalSite.ServeAsync(Context);
}
