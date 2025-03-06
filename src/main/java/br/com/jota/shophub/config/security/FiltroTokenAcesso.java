package br.com.jota.shophub.config.security;

import br.com.jota.shophub.domain.entities.Cliente;
import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.domain.repositories.ClienteRepository;
import br.com.jota.shophub.domain.repositories.FornecedorRepository;
import br.com.jota.shophub.exception.RegraDeNegorcioException;
import br.com.jota.shophub.services.TokenService;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class FiltroTokenAcesso extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final ClienteRepository clienteRepository;
    private final FornecedorRepository fornecedorRepository;

    public FiltroTokenAcesso(TokenService tokenService, ClienteRepository clienteRepository, FornecedorRepository fornecedorRepository) {
        this.tokenService = tokenService;
        this.clienteRepository = clienteRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarTokenDaRequisicao(request);

        if (token != null) {
            DecodedJWT decodedJWT = tokenService.verificarToken(token);
            String email = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

            Cliente cliente = clienteRepository.findByEmailIgnoreCase(email).orElse(null);

            if (cliente != null) {
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                Authentication authentication = new UsernamePasswordAuthenticationToken(cliente, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                Fornecedor fornecedor = fornecedorRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RegraDeNegorcioException("Fornecedor não encontrado para o email: " + email));
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                Authentication authentication = new UsernamePasswordAuthenticationToken(fornecedor, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarTokenDaRequisicao(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
