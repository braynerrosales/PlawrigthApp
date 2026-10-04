package upload_y_download;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class UploadMemoriaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("upload-y-download"));
    }

    // #region example
    @Test
    void subirVariosArchivosCreadosEnMemoriaYQuitarlos() {
        Locator adjuntos = page.getByLabel("Adjuntos");
        Locator archivos = page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Archivos seleccionados"))
            .getByRole(AriaRole.LISTITEM);

        adjuntos.setInputFiles(new FilePayload[] {
            new FilePayload("notas.txt", "text/plain", "Entregar por la tarde".getBytes(StandardCharsets.UTF_8)),
            new FilePayload("datos.csv", "text/csv", "id,total\n1,120.00\n".getBytes(StandardCharsets.UTF_8)),
        });
        assertThat(archivos).hasText(new String[] {"notas.txt (21 bytes)", "datos.csv (18 bytes)"});

        // Un arreglo vacío deja el input sin archivos.
        adjuntos.setInputFiles(new Path[0]);
        assertThat(archivos).hasCount(0);
    }
    // #endregion
}
