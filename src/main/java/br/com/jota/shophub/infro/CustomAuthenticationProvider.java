package br.com.jota.shophub.infro;

import br.com.jota.shophub.services.ClienteService;
import br.com.jota.shophub.services.FornecedorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ClienteService clienteService;
    private final FornecedorService fornecedorService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(@Lazy ClienteService clienteService,
                                        @Lazy FornecedorService fornecedorService, PasswordEncoder passwordEncoder) {
        this.clienteService = clienteService;
        this.fornecedorService = fornecedorService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String senha = authentication.getCredentials().toString();

        try {
            UserDetails cliente = clienteService.loadUserByUsername(email);
            if(passwordEncoder.matches(senha, cliente.getPassword())) {
                return new UsernamePasswordAuthenticationToken(cliente, senha, cliente.getAuthorities());
            }
        } catch (UsernameNotFoundException ignored) {}

        try {
            UserDetails fornecedor = fornecedorService.loadUserByUsername(email);
            if(passwordEncoder.matches(senha, fornecedor.getPassword())) {
                return new UsernamePasswordAuthenticationToken(fornecedor, senha, fornecedor.getAuthorities());
            }
        } catch (UsernameNotFoundException ignored) {}

        throw new BadCredentialsException("Email ou senha inválidas");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
