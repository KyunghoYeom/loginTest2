package logintest2.login2.client;

import logintest2.login2.domain.OAuthProvider;
import logintest2.login2.dto.OAuthInfoResponse;
import logintest2.login2.dto.OAuthLoginParams;
/**
 * OAuthApiClient: 카카오나 네이버 API 요청 후 응답값을 리턴해주는 (인터페이스)
 * OAuthLoginParams: 카카오, 네이버 요청에 필요한 데이터를 갖고 있는 파라미터 (인터페이스)
 * KakaoTokens, NaverTokens: 인증 API 응답
 * OAuthInfoResponse: 회원 정보 API 응답 (인터페이스)
 * RequestOAuthInfoService: 외부 API 요청의 중복되는 로직을 공통화한 클래스
 */
public interface OAuthApiClient {
    OAuthProvider oAuthProvider();//카카오냐 애플이냐
    String requestAccessToken(OAuthLoginParams params);//code로 access토큰 받아오는 메서드
    OAuthInfoResponse requestOauthInfo(String accessToken);//access토큰으로 user정보 받아오는 메서드
}
