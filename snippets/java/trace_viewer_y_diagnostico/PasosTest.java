package trace_viewer_y_diagnostico;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class PasosTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @BeforeEach
    void startTracing() {
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
    }

    // Sin path, el trace se descarta; aquí solo interesa que los grupos funcionen.
    @AfterEach
    void stopTracing() {
        context.tracing().stop();
    }

    @Test
    void agrupaLasAccionesEnElTrace() {
        // #region example
        context.tracing().group("Cargar los pedidos");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
        assertThat(page.getByRole(AriaRole.STATUS)).hasText("3 pedidos");
        context.tracing().groupEnd();

        context.tracing().group("Exportar");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Exportar")).click();
        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Exportación lista");
        context.tracing().groupEnd();
        // #endregion
    }
}
