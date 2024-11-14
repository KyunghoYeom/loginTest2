package logintest2.login2.dto;

import logintest2.login2.domain.OAuthProvider;
import org.springframework.util.MultiValueMap;

//OAuthLoginParams: 카카오, 네이버 요청에 필요한 데이터를 갖고 있는 파라미터 (인터페이스)
public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();
}
