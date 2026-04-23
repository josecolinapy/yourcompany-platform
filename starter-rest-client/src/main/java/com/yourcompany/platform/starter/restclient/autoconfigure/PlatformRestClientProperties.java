package com.yourcompany.platform.starter.restclient.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.http.HttpClient;
import java.time.Duration;

/*
 * @author josec
 * @project yourcompany-platform
 */
@ConfigurationProperties(prefix = "platform.rest-client")
public class PlatformRestClientProperties {
    /** Timeout para establecer la conexión TCP. */
    private Duration connectTimeout = Duration.ofSeconds(3);

    /** Timeout para leer la respuesta completa. */
    private Duration readTimeout = Duration.ofSeconds(10);

    /** Timeout de espera para obtener un hilo del pool. */
    private Duration shutdownGrace = Duration.ofSeconds(10);

    /** Política de redirecciones HTTP. Valores: NEVER, NORMAL, ALWAYS. */
    private HttpClient.Redirect followRedirects = HttpClient.Redirect.NORMAL;

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(final Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(final Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Duration getShutdownGrace() {
        return shutdownGrace;
    }

    public void setShutdownGrace(final Duration shutdownGrace) {
        this.shutdownGrace = shutdownGrace;
    }

    public HttpClient.Redirect getFollowRedirects() {
        return followRedirects;
    }

    public void setFollowRedirects(final HttpClient.Redirect followRedirects) {
        this.followRedirects = followRedirects;
    }
}
