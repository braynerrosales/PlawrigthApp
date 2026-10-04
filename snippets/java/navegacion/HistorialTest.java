package navegacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class HistorialTest extends TiendaTest {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(TIENDA);
    }

    // #region example
    @Test
    void volverAtrasYAdelanteEnElHistorial() {
        page.navigate("/");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Productos")).click();
        assertThat(page).hasURL(Pattern.compile("/productos$"));

        page.goBack();
        assertThat(page).hasURL("https://tienda.test/");
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Bienvenido a Tienda QA")))
            .isVisible();

        page.goForward();
        assertThat(page).hasURL(Pattern.compile("/productos$"));
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Productos"))).isVisible();
    }
    // #endregion
}
