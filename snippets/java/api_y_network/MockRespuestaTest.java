package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class MockRespuestaTest extends TestFixtures {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(ApiPractica.url());
    }

    // #region example
    @Test
    void mostrarLosPedidosQueDevuelveLaApiSimulada() {
        page.route("**/api/pedidos", route -> route.fulfill(new Route.FulfillOptions()
            .setContentType("application/json")
            .setBody("""
                [
                  {"id": 101, "cliente": "Cliente simulado", "producto": "Mesa", "cantidad": 1, "estado": "pendiente"},
                  {"id": 102, "cliente": "Otro cliente", "producto": "Lámpara", "cantidad": 5, "estado": "enviado"}
                ]
                """)));

        page.navigate("/");

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("2 pedidos");
        assertThat(page.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHasText("Cliente simulado")))
            .containsText("Mesa");
    }
    // #endregion
}
