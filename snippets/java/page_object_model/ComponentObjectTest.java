package page_object_model;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ComponentObjectTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    // #region example
    static class TarjetaProducto {
        final Locator raiz;
        final Locator nombre;
        final Locator agregar;

        TarjetaProducto(Locator raiz) {
            this.raiz = raiz;
            nombre = raiz.getByRole(AriaRole.HEADING);
            agregar = raiz.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Agregar al carrito"));
        }

        void agregarAlCarrito() {
            agregar.click();
        }
    }

    static class TiendaPage {
        final Page page;
        final Locator contadorCarrito;

        TiendaPage(Page page) {
            this.page = page;
            contadorCarrito = page.getByTestId("cart-count");
        }

        TarjetaProducto producto(String nombre) {
            Locator tarjeta = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions()
                .setHas(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(nombre))));
            return new TarjetaProducto(tarjeta);
        }
    }

    @Test
    void agregarUnProductoDesdeSuTarjeta() {
        TiendaPage tienda = new TiendaPage(page);

        tienda.producto("Mouse inalámbrico").agregarAlCarrito();

        assertThat(tienda.contadorCarrito).hasText("1");
        assertThat(tienda.producto("Monitor 4K").agregar).isDisabled();
    }
    // #endregion
}
