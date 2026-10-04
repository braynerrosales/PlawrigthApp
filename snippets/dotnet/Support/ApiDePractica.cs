using System.Diagnostics;
using System.Text;

namespace PlaywrightGuide.Snippets;

/// <summary>
/// Arranca la API de práctica <c>snippets/server/api-de-practica.mjs</c> (un servidor HTTP real: las
/// peticiones de <see cref="IAPIRequestContext"/> no pasan por <c>RouteAsync</c>). Es el mismo script que
/// usa <c>webServer</c> en <c>playwright.config.ts</c>. Si el puerto ya responde, reutiliza ese servidor.
/// Requiere <c>node</c> en el PATH. Puerto: variable <c>API_PRACTICA_PORT</c> o 4789.
/// </summary>
[SetUpFixture]
public class ApiDePractica
{
    public static string Url { get; } =
        $"http://127.0.0.1:{Environment.GetEnvironmentVariable("API_PRACTICA_PORT") ?? "4789"}";

    private static readonly HttpClient Http = new() { Timeout = TimeSpan.FromSeconds(1) };

    private Process? _server;
    private readonly StringBuilder _log = new();

    [OneTimeSetUp]
    public async Task StartAsync()
    {
        if (await RespondsAsync()) return;

        var start = new ProcessStartInfo("node")
        {
            UseShellExecute = false,
            CreateNoWindow = true,
            RedirectStandardOutput = true,
            RedirectStandardError = true,
        };
        start.ArgumentList.Add(FindScript());

        _server = Process.Start(start)
            ?? throw new InvalidOperationException("No se pudo iniciar 'node' para la API de práctica.");
        _server.OutputDataReceived += (_, e) => { if (e.Data is not null) lock (_log) _log.AppendLine(e.Data); };
        _server.ErrorDataReceived += (_, e) => { if (e.Data is not null) lock (_log) _log.AppendLine(e.Data); };
        _server.BeginOutputReadLine();
        _server.BeginErrorReadLine();

        var deadline = DateTime.UtcNow.AddSeconds(15);
        while (DateTime.UtcNow < deadline)
        {
            if (await RespondsAsync()) return;
            if (_server.HasExited) break;
            await Task.Delay(100);
        }

        Stop();
        string log;
        lock (_log) log = _log.ToString();
        throw new InvalidOperationException($"La API de práctica no respondió en {Url}.\n{log}");
    }

    [OneTimeTearDown]
    public void StopServer() => Stop();

    private void Stop()
    {
        if (_server is null) return;
        try
        {
            if (!_server.HasExited)
            {
                _server.Kill(entireProcessTree: true);
                _server.WaitForExit(5_000);
            }
        }
        catch (InvalidOperationException)
        {
            // El proceso ya terminó entre la comprobación y Kill.
        }
        _server.Dispose();
        _server = null;
    }

    private static async Task<bool> RespondsAsync()
    {
        try
        {
            using var response = await Http.GetAsync($"{Url}/api/pedidos");
            return response.IsSuccessStatusCode;
        }
        catch (HttpRequestException)
        {
            return false;
        }
        catch (TaskCanceledException)
        {
            return false;
        }
    }

    /// <summary>Busca <c>server/api-de-practica.mjs</c> subiendo desde la carpeta de salida (bin/...) hasta <c>snippets/</c>.</summary>
    private static string FindScript()
    {
        for (var dir = new DirectoryInfo(AppContext.BaseDirectory); dir is not null; dir = dir.Parent)
        {
            var script = Path.Combine(dir.FullName, "server", "api-de-practica.mjs");
            if (File.Exists(script)) return script;
        }
        throw new FileNotFoundException(
            $"No se encontró server/api-de-practica.mjs subiendo desde {AppContext.BaseDirectory}.");
    }
}
