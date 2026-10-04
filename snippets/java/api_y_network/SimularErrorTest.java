package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SimularErrorTest extends TestFixtures {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(ApiPractica.url());
    }

    // #region example
    @Test
    void avisarCuandoLaApiFalla() {
        page.route("**/api/pedidos", route -> route.fulfill(new Route.FulfillOptions()
            .setStatus(500)
            .setContentType("application/json")
            .setBody("{\"errores\": [\"error interno\"]}")));

        page.navigate("/");

        assertThat(page.getByRole(AriaRole.STATUS))
            .hasText("No se pudieron cargar los pedidos. Inténtalo de nuevo más tarde.");
        assertThat(page.getByRole(AriaRole.TABLE)).isHidden();
    }
    // #endregion
}
