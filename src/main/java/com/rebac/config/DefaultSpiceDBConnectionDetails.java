package com.rebac.config;

public record DefaultSpiceDBConnectionDetails(
        String token,
        String endpoint,
        boolean tlsEnabled
) implements SpiceDBConnectionDetails {}

