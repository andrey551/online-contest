package com.tad.gateway.permission.provider;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import com.tad.gateway.permission.PermissionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.HttpMethod.GET;
import static com.tad.gateway.constants.Paths.DEFAULT_SOLUTION_PATH;

@Component
public class SolutionPermissionProvider implements PermissionProvider {
    private final String RUN_SOLUTION_PATH = DEFAULT_SOLUTION_PATH + "{solution_id}";
    @Override
    public List<RoutePermission> getPermissions() {
        return List.of(
                new RoutePermission(GET, DEFAULT_SOLUTION_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT)),
                new RoutePermission(GET, RUN_SOLUTION_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT))
        );
    }
}
