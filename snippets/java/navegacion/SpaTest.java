package navegacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SpaTest extends TiendaTest {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(TIENDA);
    }

    // #region example
    @Test
    void navegarDentroDeUnaSpa() {
        page.navigate("/cuenta");
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Perfil"))).isVisible();

        // La app cambia la URL con history.pushState: no se carga un documento nuevo.
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Pedidos")).click();
        assertThat(page).hasURL(Pattern.compile("/cuenta/pedidos$"));
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Pedidos"))).isVisible();

        // Atrás también funciona: la app escucha popstate y vuelve a mostrar Perfil.
        page.goBack();
        assertThat(page).hasURL(Pattern.compile("/cuenta$"));
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Perfil"))).isVisible();
    }
    // #endregion
}
