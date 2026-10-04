package iframes_y_dialogs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DialogConfirmTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("iframes-y-dialogs"));
    }

    // #region example
    @Test
    void aceptarElConfirmEliminaLaTarea() {
        Locator tarea = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Revisar reporte"));

        // El handler se registra ANTES de la acción que abre el diálogo.
        List<String> mensajes = new ArrayList<>();
        page.onceDialog(dialog -> {
            mensajes.add(dialog.message());
            dialog.accept();
        });
        tarea.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Eliminar")).click();

        assertThat(tarea).isHidden();
        assertEquals(List.of("¿Eliminar «Revisar reporte»?"), mensajes);
    }
    // #endregion
}
