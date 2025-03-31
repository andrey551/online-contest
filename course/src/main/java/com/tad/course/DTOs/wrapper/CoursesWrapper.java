package com.tad.course.DTOs.wrapper;

import com.tad.course.DTOs.raw.RawCourse;

import java.util.List;

public record CoursesWrapper (
  List<RawCourse> courses
) {}
