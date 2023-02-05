package nam.gor.machineryleasing.config.filter;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FilterChainExceptionHandler extends OncePerRequestFilter {
    private final HandlerExceptionResolver resolver;

    public FilterChainExceptionHandler(
            @Qualifier("handlerExceptionResolver")
            HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req,
                                    @NonNull HttpServletResponse resp,
                                    @NonNull FilterChain chain) {
        try {
            chain.doFilter(req, resp);
        } catch (Exception e) {
            resolver.resolveException(req, resp, null, e);
        }
    }
}
