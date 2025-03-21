package br.com.jota.shophub.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FiltroTokenAcesso filtroTokenAcesso;

    public SecurityConfig(FiltroTokenAcesso filtroTokenAcesso) {
        this.filtroTokenAcesso = filtroTokenAcesso;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.GET, "/fornecedores/verificar/*").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/clientes/verificar/*").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/clientes/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/fornecedores/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/clientes").permitAll();
                    req.requestMatchers("/clientes").hasRole("CLIENTE");
                    req.requestMatchers(HttpMethod.POST, "/fornecedores").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/produtos").permitAll();
                    req.requestMatchers("/produtos").hasRole("FORNECEDOR");
                    req.requestMatchers("/pedidos").hasRole("CLIENTE");
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
                    req.anyRequest().authenticated();
                })
                .sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(filtroTokenAcesso, UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager)
                .build();
    }

    @Bean
    public PasswordEncoder encriptador() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authentication(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
