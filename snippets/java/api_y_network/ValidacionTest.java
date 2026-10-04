package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ValidacionTest extends TestFixtures {
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
    void rechazarUnPedidoSinCantidadValida() {
        APIResponse response = request.post("/api/pedidos", RequestOptions.create()
            .setData(Map.of("cliente", "Prueba API", "producto", "Webcam", "cantidad", 0)));

        assertThat(response).not().isOK();
        assertEquals(400, response.status());
        assertEquals("{\"errores\":[\"cantidad debe ser un entero mayor que 0\"]}", response.text());
    }
    // #endregion
}
