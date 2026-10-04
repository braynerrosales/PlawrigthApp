package support;

import com.microsoft.playwright.Browser;
import org.junit.jupiter.api.BeforeEach;

/** Prueba con una página cuyo contexto ya sirve Portal QA en baseURL. */
public abstract class PortalTest extends TestFixtures {
    protected static final String PORTAL = Portal.URL;

    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(PORTAL);
    }

    @BeforeEach
    void servePortal() {
        Portal.serve(context);
    }
}
