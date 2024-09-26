package com.gobierno.seguimiento_egresado.security;


import com.gobierno.seguimiento_egresado.entity.Role;
import com.gobierno.seguimiento_egresado.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("https://novedadesmonyclau.com", "http://localhost:5200")); // Combina los orígenes permitidos
                    config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/authentication/sign-in", "/api/authentication/sign-up","/api/authentication/sign-in/egresado").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/carreras").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/api/carreras/{id}").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/api/carreras").hasRole(Role.ADMIN.name())

                                .requestMatchers(HttpMethod.GET, "/api/egresados").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/api/egresados/{id}").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/api/egresados").hasRole(Role.ADMIN.name())

                                .requestMatchers(HttpMethod.GET, "/api/movimientos-egresados").hasAnyRole(Role.ADMIN.name(), Role.EGRESADO.name())
                                .requestMatchers(HttpMethod.GET, "/api/movimientos-egresados/{id}").hasAnyRole(Role.ADMIN.name(), Role.EGRESADO.name())
                                .requestMatchers(HttpMethod.POST, "/api/movimientos-egresados").hasAnyRole(Role.ADMIN.name(), Role.EGRESADO.name())

                                .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://novedadesmonyclau.com", "http://localhost:5200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}