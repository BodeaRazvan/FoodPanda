package com.example.foodpanda.security.jwt;

import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.*;

import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService implements TokenService, Clock {
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();
    final String issuer = "foodpanda";
    final String secret = BASE64.encode("please don't share this secret");

    @Override
    public String encode(Map<String, String> attributes) {
        final Claims claims = Jwts
                .claims()
                .setIssuer(issuer)
                .setIssuedAt(now());

        claims.putAll(attributes);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(HS256, secret)
                .compressWith(COMPRESSION_CODEC)
                .compact();
    }

    @Override
    public Map<String, String> decode(String token) {
        final JwtParser parser = Jwts.parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setSigningKey(secret);

        try {
            final Claims claims = parser.parseClaimsJws(token).getBody();
            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            for (final Map.Entry<String, Object> e: claims.entrySet()) {
                builder.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return builder.build();
        } catch (final IllegalArgumentException | JwtException e) {
            return ImmutableMap.of();
        }
    }

    @Override
    public Date now() {
        final DateTime now = DateTime.now();
        return now.toDate();
    }
}
