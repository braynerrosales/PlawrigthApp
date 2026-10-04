package trace_viewer_y_diagnostico;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class GrabarTraceTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void grabaUnTraceDeUnFlujoConLaTracingApi() throws Exception {
        Path tracePath = Files.createTempDirectory("traces").resolve("pedidos-trace.zip");
        // #region example
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
        try {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
            assertThat(page.getByRole(AriaRole.STATUS)).hasText("3 pedidos");
        } finally {
            // Sin stop no hay archivo: el trace queda en memoria y se pierde.
            context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
        }
        // #endregion
        assertTrue(Files.exists(tracePath));
    }
}
