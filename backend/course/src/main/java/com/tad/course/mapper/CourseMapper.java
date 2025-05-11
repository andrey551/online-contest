package com.tad.course.mapper;

import com.tad.course.DTOs.raw.RawCourse;
import com.tad.course.DTOs.request.CourseRequest;
import com.tad.course.DTOs.wrapper.CoursesWrapper;
import com.tad.course.entities.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {

    public static Course toCourse(RawCourse rawCourse) {
        Course course = new Course();

        course.setDescription(rawCourse.description());
        course.setTitle(rawCourse.courseName());
        course.setTeacherName(rawCourse.teacherName());
        course.setSemester(rawCourse.semester());

        return course;
    }

    public static Course toCourse(CourseRequest courseRequest) {
        Course course = new Course();

        course.setDescription(courseRequest.description());
        course.setTitle(courseRequest.courseName());
        course.setSemester(courseRequest.semester());

        return course;
    }

    public static RawCourse toRawCourse(Course course) {
        return new RawCourse(
                course.getId().toString(),
                course.getTeacherName(),
                course.getTitle(),
                course.getDescription(),
                course.getSemester(),
                null);
    }

    public static CoursesWrapper toCoursesWrapper(List<Course> courses) {
        List<RawCourse> wrapper = new ArrayList<>();
        for (Course course : courses) {
            wrapper.add(toRawCourse(course));
        }

        return new CoursesWrapper(wrapper);
    }


}
