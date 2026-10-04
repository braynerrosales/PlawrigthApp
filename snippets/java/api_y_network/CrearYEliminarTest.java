package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class CrearYEliminarTest extends TestFixtures {
    APIRequestContext request;

    @BeforeEach
    void crearContextoDeApi() {
        request = playwright.request().newContext(new APIRequest.NewContextOptions().setBaseURL(ApiPractica.url()));
    }

    @AfterEach
    void cerrarContextoDeApi() {
        request.dispose();
    }

    // #region example
    @Test
    void crearLeerYEliminarUnPedido() {
        APIResponse creado = request.post("/api/pedidos", RequestOptions.create()
            .setData(Map.of("cliente", "Prueba API", "producto", "Webcam", "cantidad", 2)));
        assertEquals(201, creado.status());
        int id = JsonParser.parseString(creado.text()).getAsJsonObject().get("id").getAsInt();

        APIResponse leido = request.get("/api/pedidos/" + id);
        assertThat(leido).isOK();
        JsonObject pedido = JsonParser.parseString(leido.text()).getAsJsonObject();
        assertEquals("Prueba API", pedido.get("cliente").getAsString());
        assertEquals(2, pedido.get("cantidad").getAsInt());
        assertEquals("pendiente", pedido.get("estado").getAsString());

        APIResponse eliminado = request.delete("/api/pedidos/" + id);
        assertEquals(204, eliminado.status());
        assertEquals(404, request.get("/api/pedidos/" + id).status());
    }
    // #endregion
}
