package logintest2.login2.service;

import logintest2.login2.client.OAuthApiClient;
import logintest2.login2.domain.OAuthProvider;
import logintest2.login2.dto.OAuthInfoResponse;
import logintest2.login2.dto.OAuthLoginParams;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());//카카온지 apple인지
        String accessToken = client.requestAccessToken(params);//code를 param으로 넘겨서 accessToken받아옴
        return client.requestOauthInfo(accessToken);//accessToken받아서 사용자 정보 얻어와 return
    }
}