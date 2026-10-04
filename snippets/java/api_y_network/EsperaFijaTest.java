package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class EsperaFijaTest extends TestFixtures {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(ApiPractica.url());
    }

    // #region example
    @Test
    void crearUnPedidoEsperandoUnTiempoFijo() {
        page.navigate("/");
        page.getByLabel("Cliente").fill("Prueba UI");
        page.getByLabel("Producto").fill("Silla");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Crear pedido")).click();

        // «Dos segundos deberían bastar»: sobra casi siempre y, el día que la API tarda más, falla.
        page.waitForTimeout(2_000);
        String mensaje = page.getByRole(AriaRole.ALERT).textContent();
        assertTrue(mensaje.contains("creado"));
    }
    // #endregion
}
