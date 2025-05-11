package com.tad.course.mapper;

import com.tad.course.DTOs.raw.RawLaboratory;
import com.tad.course.DTOs.request.LaboratoryRequest;
import com.tad.course.DTOs.response.LaboratoriesResponse;
import com.tad.course.DTOs.response.LaboratoryResponse;
import com.tad.course.entities.Laboratory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class LaboratoryMapper {
    public static Laboratory toLaboratory(LaboratoryRequest laboratoryRequest) {
        Laboratory laboratory = new Laboratory();

        laboratory.setTitle(laboratoryRequest.title());
        laboratory.setDescription(laboratoryRequest.description());
        laboratory.setTags(laboratoryRequest.tags());
        laboratory.setDeadline(laboratoryRequest.deadline());

        return laboratory;
    }

    public static RawLaboratory toRawLaboratory(Laboratory laboratory) {
        return new RawLaboratory(
                laboratory.getId().toString(),
                laboratory.getTitle(),
                laboratory.getDescription(),
                laboratory.getTags(),
                null,
                laboratory.getDeadline());
    }

    public static ResponseEntity toHttpResponse(LaboratoryResponse laboratoryResponse) {
        return new ResponseEntity(
                laboratoryResponse.getLaboratory(),
                HttpStatusCode.valueOf(laboratoryResponse.getStatus().getCode()));
    }

    public static ResponseEntity toHttpResponse(LaboratoriesResponse laboratoryResponse) {
        return new ResponseEntity(
                laboratoryResponse.getLaboratories(),
                HttpStatusCode.valueOf(laboratoryResponse.getStatus().getCode()));
    }
}
