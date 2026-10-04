package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ModificarRespuestaTest extends TestFixtures {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(ApiPractica.url());
    }

    // #region example
    @Test
    void mostrarLosPedidosComoEnviados() {
        page.route("**/api/pedidos", route -> {
            APIResponse response = route.fetch();
            JsonArray pedidos = JsonParser.parseString(response.text()).getAsJsonArray();
            for (JsonElement pedido : pedidos) {
                pedido.getAsJsonObject().addProperty("estado", "enviado");
            }
            route.fulfill(new Route.FulfillOptions().setResponse(response).setBody(pedidos.toString()));
        });

        page.navigate("/");

        assertThat(page.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHasText("Ana Torres")))
            .containsText("enviado");
    }
    // #endregion
}
