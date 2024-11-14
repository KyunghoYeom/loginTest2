package logintest2.login2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import logintest2.login2.domain.OAuthProvider;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppleInfoResponse implements OAuthInfoResponse {

    @JsonProperty("sub")
    private String sub; // 애플 사용자 식별자

    @JsonProperty("email")
    private String email;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getNickname() {
        // Apple에서는 기본적으로 nickname을 제공하지 않으므로 이메일 앞부분을 대체로 사용
        return email != null ? email.split("@")[0] : "AppleUser";
    }

    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.APPLE;
    }
}