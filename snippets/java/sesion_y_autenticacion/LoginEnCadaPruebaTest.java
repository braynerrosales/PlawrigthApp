package sesion_y_autenticacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class LoginEnCadaPruebaTest extends PortalTest {
    // #region example
    @BeforeEach
    void iniciarSesion() {
        // Cada prueba repite el formulario de login antes de hacer lo que de verdad quiere probar.
        page.navigate("/login");
        page.getByLabel("Usuario").fill("ana");
        page.getByLabel("Contraseña").fill("clave-de-prueba");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ingresar")).click();
        assertThat(page).hasURL(Pattern.compile("/panel$"));
    }

    @Test
    void verElSaludo() {
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hola, Ana"))).isVisible();
    }

    @Test
    void verElRol() {
        assertThat(page.getByText("Rol: cliente")).isVisible();
    }
    // #endregion
}
