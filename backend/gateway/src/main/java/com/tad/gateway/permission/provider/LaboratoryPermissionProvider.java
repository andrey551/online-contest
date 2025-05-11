package com.tad.gateway.permission.provider;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import com.tad.gateway.permission.PermissionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.HttpMethod.*;
import static com.tad.gateway.constants.Paths.DEFAULT_LABORATORY_PATH;

@Component
public class LaboratoryPermissionProvider implements PermissionProvider {
    private final String GET_LABORATORY_PATH = DEFAULT_LABORATORY_PATH + "{uuid}";
    private final String GET_LABORATORIES_BY_COURSE_ID_PATH = DEFAULT_LABORATORY_PATH + "course/{uuid}";

    @Override
    public List<RoutePermission> getPermissions() {
        return List.of(
                new RoutePermission(POST, DEFAULT_LABORATORY_PATH, List.of(ROLE.TEACHER)),
                new RoutePermission(GET, GET_LABORATORY_PATH, List.of(ROLE.TEACHER)),
                new RoutePermission(GET, GET_LABORATORIES_BY_COURSE_ID_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT))
        );
    }
}
