package support;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Path;

/**
 * API de pedidos de {@code snippets/server/api-de-practica.mjs}, el mismo script que usan TypeScript,
 * C# y Python. La arranca la primera prueba que la pide; si el puerto ya responde, reutiliza ese servidor.
 * Requiere {@code node} en el PATH. Puerto: variable {@code API_PRACTICA_PORT} o 4789.
 */
public final class ApiPractica {
    private static final String URL =
        "http://127.0.0.1:" + System.getenv().getOrDefault("API_PRACTICA_PORT", "4789");

    private static boolean lista;

    private ApiPractica() {}

    /** URL base de la API; arranca el servidor la primera vez. */
    public static synchronized String url() {
        if (lista || responde()) {
            lista = true;
            return URL;
        }
        try {
            Path script = Path.of("..", "server", "api-de-practica.mjs").toAbsolutePath().normalize();
            Process server = new ProcessBuilder("node", script.toString())
                .redirectOutput(ProcessBuilder.Redirect.DISCARD)
                .redirectError(ProcessBuilder.Redirect.DISCARD)
                .start();
            Runtime.getRuntime().addShutdownHook(new Thread(server::destroy));

            long limite = System.currentTimeMillis() + 15_000;
            while (!responde()) {
                if (!server.isAlive() || System.currentTimeMillis() > limite) {
                    server.destroy();
                    throw new IllegalStateException("La API de práctica no respondió en " + URL);
                }
                Thread.sleep(100);
            }
            lista = true;
            return URL;
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo iniciar 'node' para la API de práctica.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    private static boolean responde() {
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(URL + "/api/pedidos").toURL().openConnection();
            connection.setConnectTimeout(1_000);
            connection.setReadTimeout(1_000);
            try {
                return connection.getResponseCode() == 200;
            } finally {
                connection.disconnect();
            }
        } catch (IOException e) {
            return false;
        }
    }
}
