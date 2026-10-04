package iframes_y_dialogs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class NuevaPestanaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("iframes-y-dialogs"));
    }

    // #region example
    @Test
    void losTerminosSeLeenEnOtraPestanaYSeAceptanEnLaOriginal() {
        Page terminos = page.waitForPopup(() -> {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ver términos y condiciones")).click();
        });

        // Las dos pestañas están abiertas y se usan a la vez, sin «cambiar» a ninguna.
        assertThat(terminos).hasTitle("Términos y condiciones");
        assertThat(terminos.getByText("Versión vigente: octubre de 2026")).isVisible();
        page.getByLabel("Acepto los términos y condiciones").check();
        assertEquals(2, page.context().pages().size());

        terminos.close();
        assertEquals(1, page.context().pages().size());
        assertThat(page.getByLabel("Acepto los términos y condiciones")).isChecked();
    }
    // #endregion
}
