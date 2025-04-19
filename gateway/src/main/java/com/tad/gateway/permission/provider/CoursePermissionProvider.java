package com.tad.gateway.permission.provider;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.model.RoutePermission;
import com.tad.gateway.permission.PermissionProvider;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tad.gateway.constants.HttpMethod.*;
import static com.tad.gateway.constants.Paths.DEFAULT_COURSE_PATH;

@Component
public class CoursePermissionProvider implements PermissionProvider {
    private final String GET_COURSE_PATH = DEFAULT_COURSE_PATH + "/{course-id}";
    private final String GET_COURSES_BY_TEACHER_ID_PATH = DEFAULT_COURSE_PATH + "teachers/{uuid}";
    private final String GET_COURSE_BY_STUDENT_ID_PATH = DEFAULT_COURSE_PATH + "students/{uuid}";
    private final String POST_STUDENT_TO_COURSES_PATH = DEFAULT_COURSE_PATH + "students/";

    @Override
    public List<RoutePermission> getPermissions() {
        return List.of(
                new RoutePermission(POST, DEFAULT_COURSE_PATH, List.of(ROLE.TEACHER)),
                new RoutePermission(GET, GET_COURSE_PATH, List.of(ROLE.TEACHER)),
                new RoutePermission(GET, GET_COURSES_BY_TEACHER_ID_PATH, List.of(ROLE.TEACHER)),
                new RoutePermission(GET, GET_COURSE_BY_STUDENT_ID_PATH, List.of(ROLE.TEACHER, ROLE.STUDENT)),
                new RoutePermission(GET, GET_COURSES_BY_TEACHER_ID_PATH, List.of(ROLE.TEACHER)),
                new RoutePermission(PUT, POST_STUDENT_TO_COURSES_PATH, List.of(ROLE.TEACHER, ROLE.ADMIN))
        );
    }
}
