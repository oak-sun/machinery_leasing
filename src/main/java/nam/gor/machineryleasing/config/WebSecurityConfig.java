package nam.gor.machineryleasing.config;

import nam.gor.machineryleasing.config.filter.FilterChainExceptionHandler;
import nam.gor.machineryleasing.config.filter.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@AllArgsConstructor
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTAuthorizationFilter filter;
    private final FilterChainExceptionHandler handler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .addFilterBefore(handler, LogoutFilter.class)
                .addFilterAfter(filter,
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(
                        "/v3/api-docs/**",
                        "/docs/**",
                        "/swagger-ui/**",
                        "/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();
        config
                .setAllowedOrigins(Arrays.asList(
                        "http://localhost:8080",
                        "http://localhost:3000",
                        "https://oaksun.github.io"));
        config
                .setAllowedMethods(Arrays.asList(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "HEAD",
                        "OPTIONS"));
        config
                .setAllowedHeaders(Arrays.asList(
                        "Authorization",
                        "Content-Type"));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(
                "/**",
                config);
        return source;
    }
}
