package com.rebac.spice;

public record SpiceDBCheckPermissionRequest(
        String objectType,
        String objectId,
        String permission,
        String subjectType,
        String subjectId
) {}