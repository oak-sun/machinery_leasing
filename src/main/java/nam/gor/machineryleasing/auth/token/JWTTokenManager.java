package nam.gor.machineryleasing.auth.token;

import nam.gor.machineryleasing.models.entities.User;
import nam.gor.machineryleasing.models.exceptions.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenManager implements TokenGenerator, TokenExtractor {
    private final String secret;
    private final Long tokenDurationInMilliseconds;

    public JWTTokenManager(@Value("${jwt.secret}") String secret,
                           @Value("${jwt.duration}") Long tokenDurationInMilliseconds) {
        this.secret = secret;
        this.tokenDurationInMilliseconds = tokenDurationInMilliseconds;
    }

    @Override
    public String generateTokenForUser(final User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(
                        System.currentTimeMillis()
                                + tokenDurationInMilliseconds))
                .signWith(
                        SignatureAlgorithm.HS512,
                        secret.getBytes())
                .compact();
    }

    @Override
    public User extractUserFromToken(final String token) {
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts
                    .parser()
                    .setSigningKey(secret.getBytes())
                    .parse(token)
                    .getBody();
        } catch (ExpiredJwtException |
                 UnsupportedJwtException |
                 MalformedJwtException e) {
            throw new InvalidTokenException(e.getMessage());
        }
        var userId = claims.get("id", String.class);
        var userName = claims.get("name", String.class);
        var userEmail = claims.get("email", String.class);
        return new User(
                userId,
                userName,
                userEmail,
                null);
    }
}
