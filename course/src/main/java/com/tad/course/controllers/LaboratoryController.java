package com.tad.course.controllers;

import com.tad.course.DTOs.request.LaboratoryRequest;
import com.tad.course.DTOs.response.LaboratoriesResponse;
import com.tad.course.DTOs.response.LaboratoryResponse;
import com.tad.course.entities.Laboratory;
import com.tad.course.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.tad.course.mapper.LaboratoryMapper.toHttpResponse;

@RestController
@RequestMapping("/a[i/v1/laboratories")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @PostMapping("/")
    public ResponseEntity createLaboratory(@RequestBody LaboratoryRequest lab) {
        LaboratoryResponse response = laboratoryService.addLaboratory(lab);

        return toHttpResponse(response);
    }

    @GetMapping("/{laboratory-id}")
    public ResponseEntity getLaboratory(@PathVariable("laboratory-id") UUID laboratoryId) {
        LaboratoryResponse response = laboratoryService.getLaboratoryById(laboratoryId);

        return toHttpResponse(response);
    }

    @GetMapping("/{course-id}")
    public ResponseEntity getLaboratoryByCourse(@PathVariable("course-id") UUID courseId) {
        LaboratoriesResponse response = laboratoryService.getLaboratoriesByCourseId(courseId);

        return toHttpResponse(response);
    }
}
