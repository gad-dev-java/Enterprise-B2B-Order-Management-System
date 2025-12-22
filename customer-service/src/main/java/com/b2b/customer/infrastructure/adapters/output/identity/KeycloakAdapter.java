package com.b2b.customer.infrastructure.adapters.output.identity;

import com.b2b.customer.application.ports.output.IdentityProviderPort;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class KeycloakAdapter implements IdentityProviderPort {
    private final Keycloak keycloakClient;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Override
    public String createUser(String email, String password, String firstName, String lastName) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(email);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = keycloakClient.realm(realm).users();

        try (Response response = usersResource.create(user)) {
            if (response.getStatus() == 201) {
                String path = response.getLocation().getPath();
                return path.substring(path.lastIndexOf("/") + 1);
            } else if (response.getStatus() == 409) {
                throw new RuntimeException("El usuario o email ya existe en Keycloak");
            } else {
                throw new RuntimeException("Error creando usuario en Keycloak. Status: " + response.getStatus());
            }
        }
    }
}
