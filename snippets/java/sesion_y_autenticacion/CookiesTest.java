package sesion_y_autenticacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.List;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class CookiesTest extends PortalTest {
    // #region example
    @Test
    void entrarConUnaCookieYPerderLaSesionAlBorrarla() {
        // Token ficticio de prueba: en un proyecto real lo entrega una API o un login previo.
        context.addCookies(List.of(new Cookie("sesion", "token-ficticio-ana")
            .setUrl(PORTAL).setHttpOnly(true).setSecure(true)));

        page.navigate("/panel");
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hola, Ana"))).isVisible();

        context.clearCookies(new BrowserContext.ClearCookiesOptions().setName("sesion"));
        assertTrue(context.cookies(PORTAL).isEmpty());

        // Sin cookie, el panel redirige al login.
        page.navigate("/panel");
        assertThat(page).hasURL(Pattern.compile("/login$"));
    }
    // #endregion
}
