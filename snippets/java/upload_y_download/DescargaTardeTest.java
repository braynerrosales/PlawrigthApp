package upload_y_download;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DescargaTardeTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("upload-y-download"));
    }

    @Test
    void esperarLaDescargaDespuesDelClicLlegaTarde() {
        TimeoutError error = assertThrows(TimeoutError.class, () -> {
            // #region example
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Exportar CSV")).click();
            // La descarga ya empezó durante el clic: esta espera no la ve y vence.
            Download download = page.waitForDownload(new Page.WaitForDownloadOptions().setTimeout(2000), () -> {});
            // #endregion
            assertNotNull(download);
        });
        assertTrue(error.getMessage().contains("Timeout 2000ms exceeded"));
    }
}
