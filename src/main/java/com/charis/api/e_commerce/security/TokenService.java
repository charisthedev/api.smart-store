package com.charis.api.e_commerce.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder encoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> a.startsWith("ROLE_"))
                .map(a -> a.substring("ROLE_".length()))
                .findFirst()
                .orElse("USER");

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("e-commerce-api")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Token valid for 1 hour
                .subject(authentication.getName())
                .claim("role", role)
                .build();

        var headers = JwsHeader.with(MacAlgorithm.HS512).build();
        var params = JwtEncoderParameters.from(headers, claims);

        return encoder.encode(params).getTokenValue();
    }
}
