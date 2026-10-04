package support;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;
import com.microsoft.playwright.options.AriaRole;
import java.net.URI;
import java.nio.file.Path;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sitio de práctica {@code snippets/fixtures/sesion} (Portal QA) servido en https://portal.test
 * interceptando las peticiones del contexto; nunca sale a la red. Igual que {@code portal.ts} en TypeScript.
 * Las cuentas y sus claves son ficticias, solo para estos ejemplos.
 */
public final class Portal {
    public static final String URL = "https://portal.test";

    private record Cuenta(String clave, String nombre, String rol, String token) {}

    private static final Gson GSON = new Gson();
    private static final Map<String, Cuenta> CUENTAS = GSON.fromJson(
        Fixtures.read("sesion/cuentas.json"), new TypeToken<Map<String, Cuenta>>() {}.getType());
    private static final Pattern SESION = Pattern.compile("(?:^|;\\s*)sesion=([^;]+)");

    private Portal() {}

    /** Simula el servidor: login con cookie de sesión y un panel privado que manda a /login sin ella. */
    public static void serve(BrowserContext context) {
        context.route(URL + "/**", route -> {
            var request = route.request();
            String path = URI.create(request.url()).getPath();

            if (path.equals("/api/login") && request.method().equals("POST")) {
                JsonObject datos = GSON.fromJson(request.postData(), JsonObject.class);
                Cuenta cuenta = CUENTAS.get(datos.get("usuario").getAsString());
                if (cuenta == null || !cuenta.clave().equals(datos.get("clave").getAsString())) {
                    route.fulfill(json(401, Map.of("error", "credenciales")));
                    return;
                }
                route.fulfill(json(200, Map.of("nombre", cuenta.nombre())).setHeaders(Map.of(
                    "Set-Cookie", "sesion=" + cuenta.token() + "; Path=/; HttpOnly; Secure; SameSite=Lax")));
                return;
            }

            if (path.equals("/login")) {
                route.fulfill(html(200, Fixtures.read("sesion/login.html")));
                return;
            }

            if (path.equals("/panel")) {
                String cookie = request.headerValue("cookie");
                Matcher match = SESION.matcher(cookie != null ? cookie : "");
                String token = match.find() ? match.group(1) : null;
                Cuenta cuenta = CUENTAS.values().stream()
                    .filter(c -> c.token().equals(token)).findFirst().orElse(null);
                if (cuenta == null) {
                    route.fulfill(html(401, Fixtures.read("sesion/sin-sesion.html")));
                    return;
                }
                String html = Fixtures.read("sesion/panel.html")
                    .replace("{{nombre}}", cuenta.nombre())
                    .replace("{{rol}}", cuenta.rol());
                if (!cuenta.rol().equals("admin")) {
                    html = html.replaceAll("<!-- admin -->[\\s\\S]*<!-- /admin -->", "");
                }
                route.fulfill(html(200, html));
                return;
            }

            route.fulfill(new Route.FulfillOptions()
                .setStatus(404).setContentType("text/plain; charset=utf-8").setBody("No encontrada"));
        });
    }

    private static Route.FulfillOptions json(int status, Object body) {
        return new Route.FulfillOptions()
            .setStatus(status).setContentType("application/json").setBody(GSON.toJson(body));
    }

    private static Route.FulfillOptions html(int status, String body) {
        return new Route.FulfillOptions()
            .setStatus(status).setContentType("text/html; charset=utf-8").setBody(body);
    }

    /** Inicia sesión por la interfaz en un contexto nuevo y guarda su estado en {@code path}. */
    public static void saveSession(Browser browser, String usuario, Path path) {
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setBaseURL(URL));
        serve(context);
        Page page = context.newPage();
        page.navigate("/login");
        page.getByLabel("Usuario").fill(usuario);
        page.getByLabel("Contraseña").fill(CUENTAS.get(usuario).clave());
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ingresar")).click();
        page.waitForURL("**/panel");
        context.storageState(new BrowserContext.StorageStateOptions().setPath(path));
        context.close();
    }
}
