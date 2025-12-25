package com.rebac;

import com.authzed.api.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final PermissionsServiceGrpc.PermissionsServiceBlockingStub permissionsStub;

    //TODO : 권한 검증 로직 작성
    public boolean checkPermission(
            String resourceType, // 리소스 타입 (user / post)
            String resourceId, // 리소스 id (Alice, post1)
            String permission, // 권한 (view)
            String subjectType, // 주체의 타입 (user)
            String subjectId // 주체의 id (Alice)
    ) {
        // step 1. CheckPermissionRequest 만들기
        // step 2. permission check 호출
        // step 3. 결과 해석
        return false;
    }

    public WriteRelationshipsResponse addRelationship(
            String resourceType,
            String resourceId,
            String relation,
            String subjectType,
            String subjectId
    ) {

        // step 1. Relationship tuple 생성
        Relationship relationship = Relationship.newBuilder()
                .setResource(
                        ObjectReference.newBuilder()
                                .setObjectType(resourceType)
                                .setObjectId(resourceId)
                                .build()
                )
                .setRelation(relation)
                .setSubject(
                        SubjectReference.newBuilder()
                                .setObject(
                                        ObjectReference.newBuilder()
                                                .setObjectType(subjectType)
                                                .setObjectId(subjectId)
                                                .build()
                                )
                                .build()
                )
                .build();

        // step 2. RelationshipUpdate 생성 (CREATE)
        RelationshipUpdate relationshipUpdate = RelationshipUpdate.newBuilder()
                .setOperation(RelationshipUpdate.Operation.OPERATION_CREATE)
                .setRelationship(relationship)
                .build();

        // step 3. WriteRelationshipsRequest 생성 및 전송
        WriteRelationshipsRequest request = WriteRelationshipsRequest.newBuilder()
                .addUpdates(relationshipUpdate)
                .build();

        return permissionsStub.writeRelationships(request);
    }

}