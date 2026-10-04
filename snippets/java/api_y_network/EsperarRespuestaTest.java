package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import com.google.gson.JsonParser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class EsperarRespuestaTest extends TestFixtures {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(ApiPractica.url());
    }

    // #region example
    @Test
    void crearUnPedidoDesdeElFormulario() {
        page.navigate("/");
        page.getByLabel("Cliente").fill("Prueba UI");
        page.getByLabel("Producto").fill("Silla");
        page.getByLabel("Cantidad").fill("4");

        Response response = page.waitForResponse(
            r -> r.url().endsWith("/api/pedidos") && r.request().method().equals("POST"),
            () -> page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Crear pedido")).click());

        assertEquals(201, response.status());
        int id = JsonParser.parseString(response.text()).getAsJsonObject().get("id").getAsInt();
        assertThat(page.getByRole(AriaRole.ALERT)).hasText("Pedido #" + id + " creado");
    }
    // #endregion
}
