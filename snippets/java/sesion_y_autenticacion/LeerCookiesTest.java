package sesion_y_autenticacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class LeerCookiesTest extends PortalTest {
    // #region example
    @Test
    void laCookieDeSesionEsHttpOnlyYSecure() {
        page.navigate("/login");
        page.getByLabel("Usuario").fill("ana");
        page.getByLabel("Contraseña").fill("clave-de-prueba");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ingresar")).click();
        assertThat(page).hasURL(Pattern.compile("/panel$"));

        Cookie sesion = context.cookies(PORTAL).stream()
            .filter(cookie -> cookie.name.equals("sesion"))
            .findFirst()
            .orElseThrow();

        assertTrue(sesion.httpOnly);
        assertTrue(sesion.secure);
        assertEquals(SameSiteAttribute.LAX, sesion.sameSite);
        // El JavaScript de la página no puede leer una cookie HttpOnly; la prueba sí, desde el contexto.
        assertFalse(((String) page.evaluate("() => document.cookie")).contains("sesion="));
    }
    // #endregion
}
