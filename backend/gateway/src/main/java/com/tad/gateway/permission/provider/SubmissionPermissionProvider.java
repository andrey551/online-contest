package com.tad.gateway.permission.provider;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import com.tad.gateway.permission.PermissionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.HttpMethod.GET;
import static com.tad.gateway.constants.Paths.DEFAULT_SUBMISSION_PATH;

@Component
public class SubmissionPermissionProvider implements PermissionProvider {
    private final String GET_SUBMISSION_DETAIL_PATH = DEFAULT_SUBMISSION_PATH + "detail/id";
    private final String GET_SUBMISSIONS_LIST_PATH = DEFAULT_SUBMISSION_PATH +
                                                                "student/{student-id}/laboratory/{laboratory-id}";
    @Override
    public List<RoutePermission> getPermissions() {
        return List.of(
                new RoutePermission(GET, GET_SUBMISSION_DETAIL_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT)),
                new RoutePermission(GET, GET_SUBMISSIONS_LIST_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT))
                );
    }
}
