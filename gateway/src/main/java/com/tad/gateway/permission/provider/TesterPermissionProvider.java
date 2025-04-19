package com.tad.gateway.permission.provider;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import com.tad.gateway.permission.PermissionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.HttpMethod.*;
import static com.tad.gateway.constants.Paths.DEFAULT_TEST_PATH;

@Component
public class TesterPermissionProvider implements PermissionProvider {
    private final String SPECIFIC_TEST_PATH = DEFAULT_TEST_PATH + "{uuid}";
    private final String RUN_TEST_PATH = DEFAULT_TEST_PATH + "/run";
    @Override
    public List<RoutePermission> getPermissions() {
        return List.of(
                new RoutePermission(POST, DEFAULT_TEST_PATH, List.of(ROLE.TEACHER)),
                new RoutePermission(GET, SPECIFIC_TEST_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT)),
                new RoutePermission(DELETE, SPECIFIC_TEST_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT)),
                new RoutePermission(POST, RUN_TEST_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT))
        );
    }
}
