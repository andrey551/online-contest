package com.tad.gateway.permission.provider;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import com.tad.gateway.permission.PermissionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.HttpMethod.*;
import static com.tad.gateway.constants.Paths.DEFAULT_RESOURCE_PATH;

@Component
public class ResourcePermissionProvider implements PermissionProvider {
    private final String SPECIFIC_RESOURCE_PATH = DEFAULT_RESOURCE_PATH + "{id}";
    private final String RUN_RESOURCE_PATH = DEFAULT_RESOURCE_PATH + "run/{id}";
    @Override
    public List<RoutePermission> getPermissions() {

        return List.of(
                new RoutePermission(POST, DEFAULT_RESOURCE_PATH, List.of(ROLE.TEACHER, ROLE.ADMIN)),
                new RoutePermission(PUT, DEFAULT_RESOURCE_PATH,  List.of(ROLE.TEACHER, ROLE.ADMIN)),
                new RoutePermission(DELETE, SPECIFIC_RESOURCE_PATH,  List.of(ROLE.TEACHER, ROLE.ADMIN)),
                new RoutePermission(GET, SPECIFIC_RESOURCE_PATH,  List.of(ROLE.TEACHER, ROLE.ADMIN)),
                new RoutePermission(GET, RUN_RESOURCE_PATH,  List.of(ROLE.ADMIN))

        );
    }
}
