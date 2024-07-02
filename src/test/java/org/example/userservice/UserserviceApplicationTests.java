package org.example.userservice;

import org.example.userservice.configs.security.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.UUID;

@SpringBootTest
class UserserviceApplicationTests {

	@Test
	void addRegisteredClient() {
		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("oidc-client")
//				.clientSecret("$2a$16$vrLXiJwvRUx3yxASSmxSD.b8s2RL0d6O5s0BoyOGtPP0A1sA2LJJq")
				.clientSecret("{noop}secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.redirectUri("https://oidcdebugger.com/debug")
				.postLogoutRedirectUri("https://oidcdebugger.com/debug")
				.scope(OidcScopes.OPENID)
				.scope(OidcScopes.PROFILE)
				.scope(OidcScopes.ADDRESS)
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.build();
		registeredClientRepository.save(oidcClient);
	}

	@Autowired
	private RegisteredClientRepository registeredClientRepository;

	@Test
	void contextLoads() {
	}
}
