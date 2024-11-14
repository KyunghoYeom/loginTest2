package logintest2.login2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

/**
 * OAuthApiClient: 카카오나 네이버 API 요청 후 응답값을 리턴해주는 (인터페이스)
 * OAuthLoginParams: 카카오, 네이버 요청에 필요한 데이터를 갖고 있는 파라미터 (인터페이스)
 * KakaoTokens, NaverTokens: 인증 API 응답
 * OAuthInfoResponse: 회원 정보 API 응답 (인터페이스)
 * RequestOAuthInfoService: 외부 API 요청의 중복되는 로직을 공통화한 클래스
 */