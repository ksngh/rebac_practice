package com.rebac.config;

import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;

public interface SpiceDBConnectionDetails extends ConnectionDetails {
    String endpoint();
    String token();
    boolean tlsEnabled();
}
