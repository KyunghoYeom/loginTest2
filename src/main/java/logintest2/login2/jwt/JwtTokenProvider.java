package logintest2.login2.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    //JWT의 서명에 사용할 비밀 키(key)를 설정
    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey) {
        //토큰을 생성할때 HS512 알고리즘을 사용을 위해secret key는 64B(512bit) 이상을 사용해야 한다
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //JWT 액세스 토큰을 생성합니다.
    public String generate(String subject, Date expiredAt) {//subject : 사용자 ID나 이메일과 같은 식별자
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    //토큰에서 subject(주체) 정보를 추출합니다.
    //고유 식별자 정보 추출한다고 보면됨
    public String extractSubject(String accessToken) {
        Claims claims = parseClaims(accessToken);
        return claims.getSubject();
    }

    //어진 토큰을 파싱하여 Claims 객체로 페이로드를 추출하고, 만료된 토큰도 예외를 통해 데이터를 반환
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}