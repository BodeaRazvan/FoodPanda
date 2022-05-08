package com.example.foodpanda.security.jwt;

import java.util.Map;

public interface TokenService {
    String encode(final Map<String,String> attributes);
    Map<String, String> decode(final String token);
}
