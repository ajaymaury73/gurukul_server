package com.ajay.gurukulX.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:9090")
                .realm("master")  // Use the master realm to manage other realms
                .clientId("admin-cli")  // Default admin client
                .username("admin")  // Use Keycloak admin username
                .password("admin")  // Use Keycloak admin password
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }
}
