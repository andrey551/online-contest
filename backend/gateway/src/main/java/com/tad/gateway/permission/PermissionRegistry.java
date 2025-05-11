package com.tad.gateway.permission;

import com.tad.gateway.constants.HttpMethod;
import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.HttpMethod.*;
import static com.tad.gateway.constants.ROLE.*;

@Component
public class PermissionRegistry {
    private final List<PermissionProvider> providers;

    public PermissionRegistry(List<PermissionProvider> providers) {
        this.providers = providers;
    }

    public List<RoutePermission> getAllPermissions() {
        return providers.stream()
                .flatMap(p -> p.getPermissions().stream())
                .toList();
    }
}
