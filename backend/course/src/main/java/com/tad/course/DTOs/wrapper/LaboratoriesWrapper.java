package com.tad.course.DTOs.wrapper;

import com.tad.course.DTOs.raw.RawLaboratory;

import java.util.List;

public record LaboratoriesWrapper(
        List<RawLaboratory> laboratories
) {}
