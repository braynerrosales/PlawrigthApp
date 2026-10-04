package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ApiYUiTest extends TestFixtures {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(ApiPractica.url());
    }

    // #region example
    @Test
    void unPedidoCreadoPorApiApareceEnLaTabla() {
        String cliente = "Cliente " + System.nanoTime();
        APIResponse response = page.request().post("/api/pedidos", RequestOptions.create()
            .setData(Map.of("cliente", cliente, "producto", "Auriculares", "cantidad", 1)));
        assertEquals(201, response.status());

        page.navigate("/");

        Locator fila = page.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHasText(cliente));
        assertThat(fila).containsText("Auriculares");
    }
    // #endregion
}
