package iframes_y_dialogs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DialogTardeTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("iframes-y-dialogs"));
    }

    // #region example
    @Test
    void registrarElHandlerDespuesDelClicLlegaTarde() {
        Locator tarea = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Revisar reporte"));

        tarea.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Eliminar")).click();
        // Demasiado tarde: sin listener, Playwright ya descartó el confirm (confirm devolvió false).
        page.onceDialog(dialog -> dialog.accept());

        // La tarea no se eliminó.
        assertThat(tarea).isVisible();
    }
    // #endregion
}
