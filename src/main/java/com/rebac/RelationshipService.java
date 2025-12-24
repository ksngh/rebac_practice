package com.rebac;

import com.authzed.api.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final PermissionsServiceGrpc.PermissionsServiceBlockingStub permissionsStub;

    public WriteRelationshipsResponse addRelationship(String resourceType, String resourceId,
                                String relation, String subjectType, String subjectId) {
        // TODO: 관계 튜플을 생성하고 SpiceDB에 write 한다.
        return null;
    }

}