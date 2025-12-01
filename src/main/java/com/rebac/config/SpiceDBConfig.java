package com.rebac.config;

import com.authzed.api.v1.PermissionsServiceGrpc;
import com.authzed.api.v1.SchemaServiceGrpc;
import com.authzed.grpcutil.BearerToken;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SpiceDBProperties.class)
public class SpiceDBConfig {

    @Bean
    @ConditionalOnMissingBean(SpiceDBConnectionDetails.class)
    public SpiceDBConnectionDetails spiceDbConnectionDetails(final SpiceDBProperties properties) {
        return new DefaultSpiceDBConnectionDetails(
                properties.token(),
                properties.endpoint(),
                properties.tlsEnabled()
        );
    }

    @Bean(destroyMethod = "shutdown")
    public ManagedChannel managedChannel(SpiceDBConnectionDetails connectionDetails) {
        ManagedChannelBuilder<?> builder = ManagedChannelBuilder
                .forTarget(connectionDetails.endpoint());

        if (!connectionDetails.tlsEnabled()) {
            builder.usePlaintext();
        }

        return builder.build();
    }

    @Bean
    @ConditionalOnMissingBean
    public PermissionsServiceGrpc.PermissionsServiceBlockingStub spicedbPermissionsStub(
            final ManagedChannel channel,
            final SpiceDBConnectionDetails connectionDetails
    ) {
        if (connectionDetails.token() == null || connectionDetails.token().isBlank()) {
            throw new IllegalArgumentException(
                    "SpiceDB token must not be null or empty. Please check your configuration.");
        }
        return PermissionsServiceGrpc.newBlockingStub(channel)
                .withCallCredentials(new BearerToken(connectionDetails.token()));
    }

    @Bean
    @ConditionalOnMissingBean
    public SchemaServiceGrpc.SchemaServiceBlockingStub schemaServiceGrpc(
            final ManagedChannel channel,
            final SpiceDBConnectionDetails connectionDetails
    ) {
        if (connectionDetails.token() == null || connectionDetails.token().isBlank()) {
            throw new IllegalArgumentException(
                    "SpiceDB token must not be null or empty. Please check your configuration.");
        }

        return SchemaServiceGrpc.newBlockingStub(channel)
                .withCallCredentials(new BearerToken(connectionDetails.token()));
    }
}
