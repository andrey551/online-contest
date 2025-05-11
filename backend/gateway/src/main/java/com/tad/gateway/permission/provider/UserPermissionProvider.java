package com.tad.gateway.permission.provider;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import com.tad.gateway.permission.PermissionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.Paths.DEFAULT_USER_PATH;
import static com.tad.gateway.constants.HttpMethod.*;

@Component
public class UserPermissionProvider  implements PermissionProvider {
    private final String INC_ATTEMPTS_PATH = DEFAULT_USER_PATH + "inc-attempt/";
    @Override
    public List<RoutePermission> getPermissions() {
        return List.of(
                new RoutePermission(GET, DEFAULT_USER_PATH, List.of(ROLE.STUDENT, ROLE.TEACHER)),
                new RoutePermission(DELETE, DEFAULT_USER_PATH, List.of(ROLE.STUDENT, ROLE.TEACHER, ROLE.ADMIN)),
                new RoutePermission(POST, DEFAULT_USER_PATH, List.of(ROLE.UNAUTHORIZED)),
                new RoutePermission(PUT, DEFAULT_USER_PATH, List.of(ROLE.STUDENT, ROLE.TEACHER)),
                new RoutePermission(GET, INC_ATTEMPTS_PATH, List.of(ROLE.STUDENT))
        );
    }
}
