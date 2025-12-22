package com.b2b.customer.infrastructure.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class KeycloakConfig {
    @Value("${app.keycloak.server-url}")
    private String serverUrl;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.client-id}")
    private String clientId;

    @Value("${app.keycloak.client-secret}")
    private String clientSecret;

    @Value("${app.keycloak.connection-pool-size:10}")
    private Integer connectionPoolSize;

    @Value("${app.keycloak.connect-timeout:10}")
    private Long connectTimeout;

    @Value("${app.keycloak.read-timeout:30}")
    private Long readTimeout;

    private Keycloak keycloak;

    @Bean
    public Keycloak keycloak() {
        log.info("Initializing Keycloak Admin Client");
        log.debug("Server URL: {}, Realm: {}, Client ID: {}", serverUrl, realm, clientId);

        try {
            this.keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(
                            new ResteasyClientBuilderImpl()
                                    .connectionPoolSize(connectionPoolSize)
                                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                                    .maxPooledPerRoute(connectionPoolSize)
                                    .build()
                    )
                    .build();

            keycloak.serverInfo().getInfo();
            log.info("Keycloak Admin Client initialized successfully");

            return keycloak;
        } catch (Exception e) {
            log.error("Failed to initialize Keycloak Admin Client", e);
            throw new IllegalStateException("Cannot connect to Keycloak server at " + serverUrl, e);
        }
    }

    @PreDestroy
    public void cleanup() {
        if (keycloak != null) {
            log.info("Closing Keycloak Admin Client");
            keycloak.close();
        }
    }
}
