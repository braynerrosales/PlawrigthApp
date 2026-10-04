package assertions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import org.opentest4j.MultipleFailuresError;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SoftTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    // #region example
    @Test
    void revisarVariasCosasAunqueUnaFalle() {
        // assertAll de JUnit ejecuta todas las comprobaciones y reporta juntos los fallos.
        assertAll(
            () -> assertThat(page.getByTestId("cart-count")).hasText("0"),
            () -> assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Productos"))).isVisible(),
            () -> assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Guardar"))).isEnabled());
    }
    // #endregion

    @Test
    void assertAllReportaTodosLosFallosDeLasAsercionesDePlaywright() {
        LocatorAssertions.HasTextOptions corto = new LocatorAssertions.HasTextOptions().setTimeout(500);
        MultipleFailuresError error = assertThrows(MultipleFailuresError.class, () -> assertAll(
            () -> assertThat(page.getByTestId("cart-count")).hasText("9", corto),
            () -> assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Productos"))).isVisible(),
            () -> assertThat(page.getByText("Envío gratis")).hasText("otro texto", corto)));
        assertEquals(2, error.getFailures().size());
    }
}
