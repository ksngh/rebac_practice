package com.rebac.config;

import com.authzed.api.v1.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class SpiceDBSchemaInitializer {

    private final SchemaServiceGrpc.SchemaServiceBlockingStub schemaStub;

    @PostConstruct
    public void initSchema() throws Exception {
        var resource = new ClassPathResource("schema.zed");
        String schema = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        WriteSchemaRequest request = WriteSchemaRequest.newBuilder()
                .setSchema(schema)
                .build();

        WriteSchemaResponse response = schemaStub.writeSchema(request);

        System.out.println("Schema written successfully");
    }

}