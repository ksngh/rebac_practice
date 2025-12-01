package com.rebac.spice;

import com.authzed.api.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpiceDBRelationshipService {

    private final PermissionsServiceGrpc.PermissionsServiceBlockingStub permissions;

    public void addMockedRelationship(){
        addRelationship("post", "100", "owner", "user", "1");
        addRelationship("group", "10", "owner", "user", "1");
        addRelationship("user", "1", "follower", "user", "2");
        addRelationship("user", "1", "follower", "user", "2");
    }

    public void addRelationship(String resourceType, String resourceId,
                                String relation, String subjectType, String subjectId) {

        var update = RelationshipUpdate.newBuilder()
                .setOperation(RelationshipUpdate.Operation.OPERATION_CREATE)
                .setRelationship(Relationship.newBuilder()
                        .setResource(ObjectReference.newBuilder()
                                .setObjectType(resourceType)
                                .setObjectId(resourceId)
                                .build())
                        .setRelation(relation)
                        .setSubject(SubjectReference.newBuilder()
                                .setObject(ObjectReference.newBuilder()
                                        .setObjectType(subjectType)
                                        .setObjectId(subjectId)
                                        .build())
                                .build())
                ).build();

        WriteRelationshipsResponse writeRelationshipsResponse = permissions.writeRelationships(
                WriteRelationshipsRequest.newBuilder()
                        .addUpdates(update)
                        .build()
        );

        System.out.println("✔ Relationship created!");
    }
}