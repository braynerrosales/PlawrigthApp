package support;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Páginas de práctica de {@code snippets/fixtures}, compartidas con los demás lenguajes. */
public final class Fixtures {
    /** Maven ejecuta las pruebas desde {@code snippets/java}. */
    public static final Path DIR = Path.of("..", "fixtures").toAbsolutePath().normalize();

    private Fixtures() {}

    /** Contenido de un archivo de {@code snippets/fixtures}, por ejemplo {@code tienda/index.html}. */
    public static String read(String file) {
        try {
            return Files.readString(DIR.resolve(file));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /** URL {@code file://} de la página de práctica: la abren igual Selenium y Playwright. */
    public static String url(String name) {
        return DIR.resolve(name + ".html").toUri().toString();
    }

    /** Página de práctica {@code snippets/fixtures/<name>.html}. */
    public static String fixture(String name) {
        return read(name + ".html");
    }
}
