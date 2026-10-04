package upload_y_download;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.nio.file.Files;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DescargaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("upload-y-download"));
    }

    // #region example
    @Test
    void descargarElCsvYComprobarSuContenido() throws Exception {
        // waitForDownload empieza a esperar, ejecuta el clic y devuelve la descarga.
        Download download = page.waitForDownload(() -> {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Exportar CSV")).click();
        });

        assertEquals("pedidos.csv", download.suggestedFilename());

        // path() espera a que termine la descarga; el archivo se borra al cerrar el contexto.
        String csv = Files.readString(download.path());
        assertEquals("id,cliente,total", csv.split("\n")[0]);
        assertTrue(csv.contains("1,Ana Pérez,120.00"));
    }
    // #endregion
}
