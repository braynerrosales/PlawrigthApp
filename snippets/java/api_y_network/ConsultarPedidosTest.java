package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ConsultarPedidosTest extends TestFixtures {
    // #region example
    APIRequestContext request;

    @BeforeEach
    void crearContextoDeApi() {
        request = playwright.request().newContext(new APIRequest.NewContextOptions().setBaseURL(ApiPractica.url()));
    }

    @AfterEach
    void cerrarContextoDeApi() {
        request.dispose();
    }

    @Test
    void consultarUnPedidoPorLaApi() {
        APIResponse response = request.get("/api/pedidos/1");

        assertThat(response).isOK();
        JsonObject pedido = JsonParser.parseString(response.text()).getAsJsonObject();
        assertEquals(1, pedido.get("id").getAsInt());
        assertEquals("Ana Torres", pedido.get("cliente").getAsString());
        assertEquals("Teclado", pedido.get("producto").getAsString());
    }
    // #endregion
}
