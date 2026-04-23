package com.yourcompany.platform.starter.restclient.config;

import com.yourcompany.platform.starter.restclient.autoconfigure.PlatformRestClientProperties;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class RestClientConfig implements DisposableBean {
    private final PlatformRestClientProperties properties;
    private final ExecutorService executor;
    private final HttpClient httpClient;

    public RestClientConfig(final PlatformRestClientProperties properties) {
        this.properties = properties;
        this.executor = buildExecutor();
        this.httpClient = buildHttpClient();
    }

    // -------------------------------------------------------------------------
    // Construcción interna
    // -------------------------------------------------------------------------

    /**
     * Crea un executor de virtual threads con nombre secuencial.
     * Cada tarea (petición HTTP) obtiene un hilo virtual propio que la JVM
     * destruye automáticamente al terminar — sin pool compartido.
     */
    private static ExecutorService buildExecutor() {
        return Executors.newThreadPerTaskExecutor(
                Thread.ofVirtual().name("rest-client-", 0).factory());
    }

    private HttpClient buildHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(properties.getConnectTimeout())
                .executor(executor)
                .followRedirects(properties.getFollowRedirects())
                .build();
    }

    // -------------------------------------------------------------------------
    // API pública
    // -------------------------------------------------------------------------

    public RestClient restClient() {
        final JdkClientHttpRequestFactory requestFactory =
                new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(properties.getReadTimeout());
        return RestClient.builder().requestFactory(requestFactory).build();
    }

    public PlatformRestClientProperties properties() {
        return properties;
    }

    // -------------------------------------------------------------------------
    // Lifecycle: cierre limpio al apagar la aplicación
    // -------------------------------------------------------------------------

    @Override
    public void destroy() throws InterruptedException {
        executor.shutdown();
        final long graceSeconds = properties.getShutdownGrace().toSeconds();
        if (!executor.awaitTermination(graceSeconds, TimeUnit.SECONDS)) {
            executor.shutdownNow();
            executor.awaitTermination(2, TimeUnit.SECONDS);
        }
    }
}
