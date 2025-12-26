package com.rebac;

import com.authzed.api.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final PermissionsServiceGrpc.PermissionsServiceBlockingStub permissionsService;

    //todo : 관계 튜플 생성 후 SpiceDB를 통해 저장
    public WriteRelationshipsResponse addRelationship(
        String resourceType, // 리소스 타입 (user / post)
        String resourceId, // 리소스 id (Alice, post1)
        String permission, // 권한 (view)
        String subjectType, // 주체의 타입 (user)
        String subjectId // 주체의 id (Alice)
    ) {
        // step 1. Relationship tuple 생성
        // step 2. RelationshipUpdate 생성 (CREATE)
        // step 3. WriteRelationshipsRequest 생성 및 전송
        return null;
    }

}