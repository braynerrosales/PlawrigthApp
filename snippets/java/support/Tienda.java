package support;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Route;
import java.net.URI;
import java.util.Map;

/**
 * Sirve el sitio de práctica {@code snippets/fixtures/tienda} en https://tienda.test interceptando las
 * peticiones del contexto; nunca sale a la red. Igual que {@code serveTienda} en TypeScript.
 */
public final class Tienda {
    public static final String URL = "https://tienda.test";

    private static final Map<String, String> PAGES = Map.of(
        "/", "index",
        "/productos", "productos",
        "/login", "login",
        "/cuenta", "cuenta",
        "/cuenta/pedidos", "cuenta");

    private Tienda() {}

    public static void serve(BrowserContext context) {
        context.route(URL + "/**", route -> {
            String page = PAGES.get(URI.create(route.request().url()).getPath());
            route.fulfill(new Route.FulfillOptions()
                .setStatus(page != null ? 200 : 404)
                .setContentType("text/html; charset=utf-8")
                .setBody(Fixtures.fixture("tienda/" + (page != null ? page : "no-encontrada"))));
        });
    }
}
