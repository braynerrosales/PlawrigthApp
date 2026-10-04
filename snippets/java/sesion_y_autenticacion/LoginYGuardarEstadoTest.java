package sesion_y_autenticacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class LoginYGuardarEstadoTest extends PortalTest {
    Path ruta;

    @BeforeEach
    void crearRuta() throws Exception {
        ruta = Files.createTempFile("portal-qa-ana-", ".json");
    }

    @AfterEach
    void borrarRuta() throws Exception {
        Files.deleteIfExists(ruta);
    }

    // #region example
    @Test
    void iniciarSesionUnaVezYGuardarElEstado() {
        page.navigate("/login");
        page.getByLabel("Usuario").fill("ana");
        page.getByLabel("Contraseña").fill("clave-de-prueba");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ingresar")).click();
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hola, Ana"))).isVisible();

        // Escribe cookies y localStorage en el archivo (fuera del repositorio) y devuelve el mismo estado como JSON.
        String estado = context.storageState(new BrowserContext.StorageStateOptions().setPath(ruta));

        JsonObject json = JsonParser.parseString(estado).getAsJsonObject();
        JsonObject cookie = json.getAsJsonArray("cookies").get(0).getAsJsonObject();
        assertEquals("sesion", cookie.get("name").getAsString());
        assertTrue(cookie.get("httpOnly").getAsBoolean());
        JsonObject guardado = json.getAsJsonArray("origins").get(0).getAsJsonObject()
            .getAsJsonArray("localStorage").get(0).getAsJsonObject();
        assertEquals("ultimoUsuario", guardado.get("name").getAsString());
        assertTrue(Files.exists(ruta));
    }
    // #endregion
}
