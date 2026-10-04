package navegacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class RedireccionTest extends TiendaTest {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(TIENDA);
    }

    // #region example
    @Test
    void elLoginRedirigeALaCuenta() {
        page.navigate("/login");
        page.getByLabel("Usuario").fill("ana");
        page.getByLabel("Contraseña").fill("clave-de-prueba");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ingresar")).click();

        // La redirección ocurre un momento después del clic: hasURL reintenta hasta que la URL coincide.
        assertThat(page).hasURL(Pattern.compile("/cuenta$"));
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Mi cuenta"))).isVisible();
    }
    // #endregion
}
