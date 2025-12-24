package com.rebac;

import com.authzed.api.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final PermissionsServiceGrpc.PermissionsServiceBlockingStub permissionsStub;

    public WriteRelationshipsResponse addRelationship(
            String resourceType,
            String resourceId,
            String relation,
            String subjectType,
            String subjectId
    ) {
        // step 1. Relationship tuple 생성
        // step 2. RelationshipUpdate 생성 (CREATE)
        // step 3. WriteRelationshipsRequest 생성 및 전송
        return null;
    }

}