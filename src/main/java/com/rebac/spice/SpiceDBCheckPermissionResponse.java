package com.rebac.spice;

public record SpiceDBCheckPermissionResponse(
        boolean isAllowed,
        String message
) {

    public static SpiceDBCheckPermissionResponse denied() {
        return new SpiceDBCheckPermissionResponse(false, "Permission denied");
    }

    public static SpiceDBCheckPermissionResponse allowed() {
        return new SpiceDBCheckPermissionResponse(true, "Permission granted");
    }
}

