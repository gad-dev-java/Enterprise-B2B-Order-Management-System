package com.b2b.customer.application.ports.output;

public interface IdentityProviderPort {
    String createUser(String email, String password, String firstName, String lastName);
}
