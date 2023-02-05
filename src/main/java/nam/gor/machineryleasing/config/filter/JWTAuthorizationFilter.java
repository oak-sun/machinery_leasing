package nam.gor.machineryleasing.config.filter;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import nam.gor.machineryleasing.auth.token.TokenExtractor;
import nam.gor.machineryleasing.models.exceptions.InvalidTokenException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final TokenExtractor extractor;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        var uri = req.getRequestURI();
        return uri.startsWith("/auth");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req,
                                    @NonNull HttpServletResponse resp,
                                    @NonNull FilterChain chain)
            throws ServletException, IOException {
        try {
            var token = extractTokenFromRequest(req);
            var user = extractor.extractUserFromToken(token);
            var auth = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    Collections.emptyList());
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);
            chain.doFilter(req, resp);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            throw e;
        }
    }

    private String extractTokenFromRequest(HttpServletRequest req) {
        return Optional
                .ofNullable(req.getHeader(AUTHORIZATION_HEADER)).
                filter(header -> header.startsWith(PREFIX)).
                map(header -> header.replace(PREFIX, ""))
                .orElseThrow(() -> new InvalidTokenException(
                        "The 'Authorization' header must be " +
                                "in the following format: " +
                                "'Bearer token'"));
    }
}
