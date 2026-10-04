package upload_y_download;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.nio.charset.StandardCharsets;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FileChooserTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("upload-y-download"));
    }

    // #region example
    @Test
    void subirUnaFotoConUnBotonPersonalizado() {
        // waitForFileChooser empieza a esperar el diálogo y ejecuta el clic que lo abre.
        FileChooser fileChooser = page.waitForFileChooser(() -> {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Subir foto")).click();
        });

        fileChooser.setFiles(new FilePayload("perfil.png", "image/png", "foto".getBytes(StandardCharsets.UTF_8)));

        assertThat(page.getByText("Foto: perfil.png")).isVisible();
    }
    // #endregion
}
