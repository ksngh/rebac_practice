package com.rebac.config;

import org.springframework.boot.docker.compose.core.RunningService;
import org.springframework.boot.docker.compose.service.connection.DockerComposeConnectionDetailsFactory;
import org.springframework.boot.docker.compose.service.connection.DockerComposeConnectionSource;

import java.util.logging.Logger;

public class SpiceDBDockerComposeConnectionDetailsFactory
        extends DockerComposeConnectionDetailsFactory<SpiceDBConnectionDetails> {

    private static final Logger logger = Logger.getLogger(
            SpiceDBDockerComposeConnectionDetailsFactory.class.getName());
    private static final String[] SERVICE_NAMES = { "spicedb", "authzed/spicedb" };

    protected SpiceDBDockerComposeConnectionDetailsFactory() {
        super(SERVICE_NAMES);
    }

    @Override
    protected SpiceDBConnectionDetails getDockerComposeConnectionDetails(final DockerComposeConnectionSource source) {
        final RunningService service = source.getRunningService();
        logger.info("Creating SpiceDB connection details for service: " + service.name());

        return new SpiceDbDockerComposeConnectionDetails(service);
    }

    static final class SpiceDbDockerComposeConnectionDetails
            extends DockerComposeConnectionDetailsFactory.DockerComposeConnectionDetails
            implements SpiceDBConnectionDetails {

        private final String endpoint;
        private final String token;
        private final boolean tlsEnabled;

        SpiceDbDockerComposeConnectionDetails(final RunningService service) {
            super(service);

            final String host = service.host();
            final int port = service.ports().get(50051);
            endpoint = host + ":" + port;
            token = service.env().getOrDefault("SPICEDB_GRPC_PRESHARED_KEY", "");
            tlsEnabled = Boolean.parseBoolean(service.env().getOrDefault("SPICEDB_GRPC_TLS_ENABLED", "false"));
        }

        @Override
        public String endpoint() {
            return endpoint;
        }

        @Override
        public String token() {
            return token;
        }

        @Override
        public boolean tlsEnabled() {
            return tlsEnabled;
        }
    }
}