package com.rebac.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.spicedb")
public record SpiceDBProperties(
        String token,
        String endpoint,
        boolean tlsEnabled
) {
}
