package sesion_y_autenticacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ReutilizarEstadoTest extends PortalTest {
    Path estadoDeAna;

    // Inicia sesión por la interfaz una sola vez para toda la clase y guarda el estado fuera del repositorio.
    @BeforeAll
    void iniciarSesionUnaVez() throws Exception {
        estadoDeAna = Files.createTempFile("portal-qa-ana-", ".json");
        Portal.saveSession(browser, "ana", estadoDeAna);
    }

    @AfterAll
    void borrarEstado() throws Exception {
        Files.deleteIfExists(estadoDeAna);
    }

    // #region example
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions()
            .setBaseURL(PORTAL)
            .setStorageStatePath(estadoDeAna);
    }

    @Test
    void elPanelAbreDirectamenteConLaSesionGuardada() {
        // Sin pasar por /login: el contexto ya trae la cookie de sesión.
        page.navigate("/panel");

        assertThat(page).hasURL(Pattern.compile("/panel$"));
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hola, Ana"))).isVisible();
    }

    @Test
    void elClienteNoVeLaSeccionDeAdministracion() {
        page.navigate("/panel");

        assertThat(page.getByText("Rol: cliente")).isVisible();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Administración"))).isHidden();
    }
    // #endregion
}
