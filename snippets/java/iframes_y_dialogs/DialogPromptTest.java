package iframes_y_dialogs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DialogPromptTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("iframes-y-dialogs"));
    }

    // #region example
    @Test
    void responderAlPromptRenombraLaTarea() {
        Locator tareas = page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Tareas"));

        // accept con texto es la respuesta que escribiría la persona en el prompt.
        page.onceDialog(dialog -> dialog.accept("Revisar reporte final"));
        tareas.getByRole(AriaRole.LISTITEM)
            .filter(new Locator.FilterOptions().setHasText("Revisar reporte"))
            .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Renombrar"))
            .click();

        assertThat(tareas.getByRole(AriaRole.LISTITEM).first()).containsText("Revisar reporte final");
    }
    // #endregion
}
