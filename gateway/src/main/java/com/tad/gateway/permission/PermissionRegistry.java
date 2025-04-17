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
    public List<RoutePermission> getPermissions() {
        return List.of(
                new RoutePermission(POST, "api/v1/courses/", List.of(STUDENT, TEACHER))
        );
    }
}
