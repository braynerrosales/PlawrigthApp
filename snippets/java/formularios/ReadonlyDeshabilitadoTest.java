package formularios;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ReadonlyDeshabilitadoTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("formularios"));
    }

    // #region example
    @Test
    void camposDeSoloLecturaYDeshabilitados() {
        Locator pedido = page.getByLabel("Número de pedido");
        Locator codigo = page.getByLabel("Código de descuento");

        // readonly: se ve y se envía, pero no se puede editar.
        assertThat(pedido).hasValue("PED-1042");
        assertThat(pedido).not().isEditable();

        // disabled: la app lo habilita solo cuando marcas la casilla.
        assertThat(codigo).isDisabled();
        page.getByLabel("Tengo un cupón").check();
        assertThat(codigo).isEnabled();
        codigo.fill("QA10");
        assertThat(codigo).hasValue("QA10");
    }
    // #endregion
}
