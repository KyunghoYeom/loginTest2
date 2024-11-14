package logintest2.login2.controller;

import logintest2.login2.dto.AppleLoginParams;
import logintest2.login2.dto.KakaoLoginParams;
import logintest2.login2.jwt.AuthTokens;
import logintest2.login2.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        //code인가
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    //@GetMapping("/apple")
    public ResponseEntity<AuthTokens> loginApple(@RequestBody AppleLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}