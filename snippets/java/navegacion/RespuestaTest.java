package navegacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class RespuestaTest extends TiendaTest {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(TIENDA);
    }

    // #region example
    @Test
    void unaRutaInexistenteResponde404SinLanzarExcepcion() {
        // navigate devuelve la respuesta del documento principal y no falla por un 404 o un 500.
        Response response = page.navigate("/no-existe");

        assertNotNull(response);
        assertEquals(404, response.status());
        assertFalse(response.ok());
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Página no encontrada")))
            .isVisible();
    }
    // #endregion
}
