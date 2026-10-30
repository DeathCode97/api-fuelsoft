package com.fuelsoft.apifuel.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()          // login: público
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // solo admin
                .requestMatchers("/auth/logout").authenticated()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")   // <-- nueva
                .requestMatchers(HttpMethod.GET, "/api/pruebas/**").authenticated()
                //otras reglas
                .anyRequest().authenticated()                      // todo lo demás: con token
            )
            .oauth2ResourceServer(oauth -> oauth
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
            );
        return http.build();
    }
	
	 private JwtAuthenticationConverter jwtAuthConverter() {
	        var authorities = new JwtGrantedAuthoritiesConverter();
	        authorities.setAuthoritiesClaimName("roles");
	        authorities.setAuthorityPrefix("ROLE_");
	        var converter = new JwtAuthenticationConverter();
	        converter.setJwtGrantedAuthoritiesConverter(authorities);
	        return converter;
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        var cfg = new CorsConfiguration();
	        cfg.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:3000")); // tu front
	        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
	        cfg.setAllowedHeaders(List.of("Authorization", "Content-Type"));
	        var source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", cfg);
	        return source;
	    }
}
