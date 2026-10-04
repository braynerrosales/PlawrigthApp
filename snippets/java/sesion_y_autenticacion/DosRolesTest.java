package sesion_y_autenticacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DosRolesTest extends TestFixtures {
    // Fuera del repositorio: los archivos contienen las sesiones.
    Path estadoDeAdmin;
    Path estadoDeAna;

    @BeforeEach
    void guardarSesiones() throws Exception {
        estadoDeAdmin = Files.createTempFile("portal-qa-admin-", ".json");
        estadoDeAna = Files.createTempFile("portal-qa-ana-", ".json");
        Portal.saveSession(browser, "admin", estadoDeAdmin);
        Portal.saveSession(browser, "ana", estadoDeAna);
    }

    @AfterEach
    void borrarEstados() throws Exception {
        Files.deleteIfExists(estadoDeAdmin);
        Files.deleteIfExists(estadoDeAna);
    }

    // #region example
    @Test
    void adminYClienteEnLaMismaPrueba() {
        BrowserContext adminContext = browser.newContext(new Browser.NewContextOptions()
            .setBaseURL(Portal.URL)
            .setStorageStatePath(estadoDeAdmin));
        BrowserContext clienteContext = browser.newContext(new Browser.NewContextOptions()
            .setBaseURL(Portal.URL)
            .setStorageStatePath(estadoDeAna));
        Portal.serve(adminContext);
        Portal.serve(clienteContext);

        Page adminPage = adminContext.newPage();
        Page clientePage = clienteContext.newPage();
        adminPage.navigate("/panel");
        clientePage.navigate("/panel");

        assertThat(adminPage.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Administración"))).isVisible();
        assertThat(clientePage.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hola, Ana"))).isVisible();
        assertThat(clientePage.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Administración"))).isHidden();

        adminContext.close();
        clienteContext.close();
    }
    // #endregion
}
