package com.tad.course.services;

import com.tad.course.DTOs.enums.TransactionStatus;
import com.tad.course.DTOs.raw.RawLaboratory;
import com.tad.course.DTOs.request.LaboratoryRequest;
import com.tad.course.DTOs.response.LaboratoriesResponse;
import com.tad.course.DTOs.response.LaboratoryResponse;
import com.tad.course.DTOs.wrapper.LaboratoriesWrapper;
import com.tad.course.entities.Laboratory;
import com.tad.course.exceptions.LaboratoryNotFound;
import com.tad.course.repositories.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tad.course.mapper.LaboratoryMapper.toLaboratory;
import static com.tad.course.mapper.LaboratoryMapper.toRawLaboratory;

@Service
public class LaboratoryService {
    @Autowired
    LaboratoryRepository laboratoryRepository;

    public LaboratoryResponse addLaboratory(LaboratoryRequest laboratoryRequest) {
        try {
            Laboratory laboratory = toLaboratory(laboratoryRequest);
            laboratory.setId(UUID.randomUUID());

            Laboratory laboratorySaved = laboratoryRepository.save(laboratory);

            return new LaboratoryResponse(TransactionStatus.SUCCESS,
                                          null,
                                          toRawLaboratory(laboratorySaved));
        } catch (Exception e) {
            return new LaboratoryResponse(TransactionStatus.INTERNAL_ERROR,
                                          "Internal error",
                                            null);
        }
    }

    public LaboratoryResponse getLaboratoryById(UUID id) {
        try {
            Laboratory laboratory = laboratoryRepository.findById(id).orElse(null);

            if (laboratory == null) {
                throw new LaboratoryNotFound();
            }

            return new LaboratoryResponse(TransactionStatus.SUCCESS,
                                        null,
                                        toRawLaboratory(laboratory));
        } catch ( LaboratoryNotFound e) {
            return new LaboratoryResponse(TransactionStatus.NOT_FOUND,
                                            "Laboratory not found",
                                            null);
        } catch (Exception e) {
            return new LaboratoryResponse(TransactionStatus.INTERNAL_ERROR,
                                    "Internal error",
                                    null);
        }
    }

    public LaboratoriesResponse getLaboratoriesByCourseId(UUID courseId) {
        try {
            List<Laboratory> laboratoryList = laboratoryRepository.findLaboratoriesByCourseId(courseId).orElse(null);
            List<RawLaboratory> rawLaboratoryList = new ArrayList<>();

            if(laboratoryList != null) {
                for (Laboratory laboratory : laboratoryList) {
                    rawLaboratoryList.add(toRawLaboratory(laboratory));
                }
            }

            return new LaboratoriesResponse(TransactionStatus.SUCCESS,
                                            null,
                                            new LaboratoriesWrapper(rawLaboratoryList));
        } catch (Exception e) {
            return new LaboratoriesResponse(TransactionStatus.INTERNAL_ERROR,
                                            "Internal error",
                                  null);
        }
    }
}
