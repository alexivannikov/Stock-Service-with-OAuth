package root.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
class StockApiServiceImpl implements StockApiService {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;
    @Value("${service.stock.audience}")
    private String audience;

    @Value("${service.stock.uri}")
    private String uri;
    @Value("${service.stock.path.stock-quotes}")
    private String stockQuotes;

    private final RestTemplate restTemplate;
    private final TokenApiService tokenApiService;

    @Override
    public String getStockQuotes(List<String> tickets) {
        String token = tokenApiService.getToken(clientId, clientSecret, audience);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        RequestEntity<List<String>> requestEntity = RequestEntity
                .post(uri + stockQuotes)
                .headers(httpHeaders)
                .body(tickets);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }
}
