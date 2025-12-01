package com.rebac.spice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpiceDBCheckController {

    private final SpiceDBRelationshipService spiceDBRelationshipService;
    private final SpiceDBPermissionService spiceDBPermissionService;

    @PostMapping("/test")
    public ResponseEntity<Void> createRelationshipSpiceDB() {
        spiceDBRelationshipService.addMockedRelationship();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<SpiceDBCheckPermissionResponse> getRelationshipCheck(
            @RequestParam String objectType,
            @RequestParam String objectId,
            @RequestParam String permission,
            @RequestParam String subjectType,
            @RequestParam String subjectId
    ){
        SpiceDBCheckPermissionRequest request = new SpiceDBCheckPermissionRequest(
                objectType, objectId, permission, subjectType, subjectId
        );
        SpiceDBCheckPermissionResponse spiceDBCheckPermissionResponse =
                spiceDBPermissionService.checkMockedPermission(request);
        return ResponseEntity.ok(spiceDBCheckPermissionResponse);
    }
}
