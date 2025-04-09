package com.tad.course.services;

import com.google.protobuf.ByteString;
import com.tad.course.constants.enums.TransactionStatus;
import com.tad.course.DTOs.raw.RawLaboratory;
import com.tad.course.DTOs.request.LaboratoryRequest;
import com.tad.course.DTOs.response.LaboratoriesResponse;
import com.tad.course.DTOs.response.LaboratoryResponse;
import com.tad.course.DTOs.wrapper.LaboratoriesWrapper;
import com.tad.course.entities.Course;
import com.tad.course.entities.Laboratory;
import com.tad.course.exceptions.LaboratoryNotFoundException;
import com.tad.course.repositories.CourseRepository;
import com.tad.course.repositories.LaboratoryRepository;
import com.tad.file.grpc.File;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tad.course.constants.FunctionMode.CREATE_DIR_MODE;
import static com.tad.course.constants.TransactionMessage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tad.course.mapper.LaboratoryMapper.toLaboratory;
import static com.tad.course.mapper.LaboratoryMapper.toRawLaboratory;

@Slf4j
@Service
public class LaboratoryService {
    Logger logger = LoggerFactory.getLogger(LaboratoryService.class);

    private final LaboratoryRepository laboratoryRepository;
    private final CourseRepository courseRepository;
    private final FileTaskGrpcClient client;

    @Autowired
    public LaboratoryService(LaboratoryRepository laboratoryRepository,
                             CourseRepository courseRepository,
                             FileTaskGrpcClient client) {
        this.laboratoryRepository = laboratoryRepository;
        this.courseRepository = courseRepository;
        this.client = client;
    }


    public LaboratoryResponse addLaboratory(LaboratoryRequest laboratoryRequest) {
        try {
            Laboratory laboratory = toLaboratory(laboratoryRequest);
            laboratory.setId(UUID.randomUUID());
            Course course = courseRepository.findById(laboratoryRequest.courseId())
                    .orElseThrow(LaboratoryNotFoundException::new);

            laboratory.setCourse(course);

            String path = String.format("%s/%s", course.getDirectory(), laboratory.getTitle());

            File.FileTaskResponse response = client.sendFileTask(ByteString.EMPTY.toByteArray(),
                                                                "",
                                                                CREATE_DIR_MODE,
                                                                path);

            if(response.getSuccess()) {
                laboratory.setDirectory(response.getMessage());
            } else {
                log.info("Can't create dir on cloud");
            }

            Laboratory newLaboratory = laboratoryRepository.save(laboratory);

            return new LaboratoryResponse(TransactionStatus.SUCCESS,
                                          SUCCESS_MESSAGE,
                                          toRawLaboratory(newLaboratory));
        }catch (LaboratoryNotFoundException e) {
            return new LaboratoryResponse(TransactionStatus.NOT_FOUND,
                                            LABORATORY_NOT_FOUND_ERROR_MESSAGE,
                                            null);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new LaboratoryResponse(TransactionStatus.INTERNAL_ERROR,
                                          INTERNAL_ERROR_MESSAGE,
                                            null);

        }
    }

    public LaboratoryResponse getLaboratoryById(UUID id) {
        try {
            Laboratory laboratory = laboratoryRepository.findById(id)
                                                        .orElseThrow(LaboratoryNotFoundException::new);


            return new LaboratoryResponse(TransactionStatus.SUCCESS,
                                        null,
                                        toRawLaboratory(laboratory));
        } catch ( LaboratoryNotFoundException e) {
            return new LaboratoryResponse(TransactionStatus.NOT_FOUND,
                                            LABORATORY_NOT_FOUND_ERROR_MESSAGE,
                                            null);
        } catch (Exception e) {
            return new LaboratoryResponse(TransactionStatus.INTERNAL_ERROR,
                                    INTERNAL_ERROR_MESSAGE,
                                    null);
        }
    }

    public LaboratoriesResponse getLaboratoriesByCourseId(UUID courseId) {
        try {
            List<Laboratory> laboratoryList = laboratoryRepository.findLaboratoriesByCourseId(courseId)
                                                                  .orElse(null);

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
                                            INTERNAL_ERROR_MESSAGE,
                                  null);
        }
    }
}
