package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DragToTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void moverUnaTareaAHecho() {
        Locator tarea = page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Pendiente"))
            .getByText("Revisar reporte");
        Locator hecho = page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Hecho"));

        tarea.dragTo(hecho);

        assertThat(hecho.getByRole(AriaRole.LISTITEM)).hasText(new String[] {"Revisar reporte"});
    }
    // #endregion
}
