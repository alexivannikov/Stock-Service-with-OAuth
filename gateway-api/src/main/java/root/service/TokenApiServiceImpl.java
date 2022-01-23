package root.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import root.model.AuthResponse;
import root.model.AuthRequest;

@RequiredArgsConstructor
@Component
class TokenApiServiceImpl implements TokenApiService {

    private static final String GRANT_TYPE = "client_credentials";

    @Value("${spring.security.oauth2.client.provider.auth0.issuer-uri}")
    private String tokenUri;

    private final RestTemplate restTemplate;

    @Override
    public String getToken(String clientId, String clientSecret, String audience) {
        AuthResponse authResponse = restTemplate.postForObject(
                tokenUri + "oauth/token",
                getAuthRequest(clientId, clientSecret, audience),
                AuthResponse.class
        );
        return authResponse.getAccessToken();
    }

    private AuthRequest getAuthRequest(String clientId, String clientSecret, String audience) {
        return AuthRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .audience(audience)
                .grantType(GRANT_TYPE)
                .build();
    }
}
