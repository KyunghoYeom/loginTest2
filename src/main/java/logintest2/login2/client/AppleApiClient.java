package logintest2.login2.client;

import logintest2.login2.domain.OAuthProvider;
import logintest2.login2.dto.AppleInfoResponse;
import logintest2.login2.dto.AppleLoginParams;
import logintest2.login2.dto.AppleTokens;
import logintest2.login2.dto.OAuthInfoResponse;
import logintest2.login2.dto.OAuthLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AppleApiClient implements OAuthApiClient {

    @Value("${oauth.apple.url.auth}")
    private String authUrl;

    private final RestTemplate restTemplate;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.APPLE;
    }

    @Override
    public String requestAccessToken(OAuthLoginParams params) {
        String url = authUrl + "/auth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> request = new HttpEntity<>(params.makeBody(), headers);

        AppleTokens response = restTemplate.postForObject(url, request, AppleTokens.class);
        assert response != null;
        return response.getAccessToken();
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        String url = "https://appleid.apple.com/auth/keys"; // Apple의 사용자 정보 요청 URL

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> request = new HttpEntity<>(headers);

        return restTemplate.postForObject(url, request, AppleInfoResponse.class);
    }
}