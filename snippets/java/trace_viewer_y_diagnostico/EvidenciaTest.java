package trace_viewer_y_diagnostico;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class EvidenciaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void adjuntaUnaCapturaComoEvidenciaAlReporte(TestReporter testReporter) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
        assertThat(page.getByRole(AriaRole.STATUS)).hasText("3 pedidos");
        // #region example
        // testReporter llega como parámetro del método de prueba (JUnit 5.12 o superior).
        testReporter.publishFile("pedidos-cargados.png", MediaType.IMAGE_PNG,
            archivo -> page.screenshot(new Page.ScreenshotOptions().setPath(archivo).setFullPage(true)));
        // #endregion
    }
}
