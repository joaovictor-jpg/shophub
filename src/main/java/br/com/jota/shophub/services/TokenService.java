package br.com.jota.shophub.services;

import br.com.jota.shophub.exception.RegraDeNegorcioException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

@Service
public class TokenService {
    private final String secret;

    public TokenService(@Value("${app.jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String gerarToken(String email, Collection<? extends GrantedAuthority> authorities) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Shop Hub")
                    .withSubject(email)
                    .withClaim("roles", authorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .withExpiresAt(expiracao(30))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RegraDeNegorcioException("Error ao gerar token jwt de acesso!");
        }
    }

    public DecodedJWT verificarToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Shop Hub")
                    .build();

            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new RegraDeNegorcioException("Error ao verificar token jwt de acesso!");
        }
    }

    private Instant expiracao(Integer minutos) {
        return LocalDateTime.now().plusMinutes(minutos).toInstant(ZoneOffset.of("-03:00"));
    }

}
