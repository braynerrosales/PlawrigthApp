package support;

import org.junit.jupiter.api.BeforeEach;

/** Prueba cuyo contexto ya sirve el sitio de práctica en https://tienda.test. */
public abstract class TiendaTest extends TestFixtures {
    protected static final String TIENDA = Tienda.URL;

    @BeforeEach
    void serveTienda() {
        Tienda.serve(context);
    }
}
