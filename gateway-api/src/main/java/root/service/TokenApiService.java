package root.service;

public interface TokenApiService {
    public String getToken(String clientId, String clientSecret, String audience);

}
