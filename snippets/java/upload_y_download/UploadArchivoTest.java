package upload_y_download;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.nio.file.Path;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class UploadArchivoTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("upload-y-download"));
    }

    // #region example
    @Test
    void subirUnArchivoDesdeElDisco() {
        // Maven ejecuta las pruebas desde la carpeta del proyecto: la ruta no depende del IDE ni de la terminal.
        Path factura = Path.of("..", "fixtures", "archivos", "factura.txt").toAbsolutePath();

        page.getByLabel("Adjuntos").setInputFiles(factura);

        Locator archivos = page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Archivos seleccionados"))
            .getByRole(AriaRole.LISTITEM);
        assertThat(archivos).hasText(new Pattern[] {Pattern.compile("^factura\\.txt \\(\\d+ bytes\\)$")});
    }
    // #endregion
}
