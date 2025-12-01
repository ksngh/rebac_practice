package com.rebac.spice;

import com.authzed.api.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpiceDBPermissionService {

    private final PermissionsServiceGrpc.PermissionsServiceBlockingStub permissions;

    public boolean checkPermission(String resourceType, String resourceId,
                                   String permission, String subjectType, String subjectId) {

        var request = CheckPermissionRequest.newBuilder()
                .setResource(ObjectReference.newBuilder()
                        .setObjectType(resourceType)
                        .setObjectId(resourceId)
                        .build())
                .setPermission(permission)
                .setSubject(SubjectReference.newBuilder()
                        .setObject(ObjectReference.newBuilder()
                                .setObjectType(subjectType)
                                .setObjectId(subjectId)
                                .build())
                        .build())
                .build();

        var response = permissions.checkPermission(request);
        return response.getPermissionship()
                == CheckPermissionResponse.Permissionship.PERMISSIONSHIP_HAS_PERMISSION;
    }

    public SpiceDBCheckPermissionResponse checkMockedPermission(SpiceDBCheckPermissionRequest request){
        boolean checked = checkPermission(
                request.objectType(),
                request.objectId(),
                request.permission(),
                request.subjectType(),
                request.subjectId()
        );
        return checked ? SpiceDBCheckPermissionResponse.allowed() : SpiceDBCheckPermissionResponse.denied();
    }
}