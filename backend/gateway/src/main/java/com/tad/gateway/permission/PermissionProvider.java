package com.tad.gateway.permission;

import com.tad.gateway.model.RoutePermission;

import java.util.List;

public interface PermissionProvider {
    List<RoutePermission> getPermissions();
}
