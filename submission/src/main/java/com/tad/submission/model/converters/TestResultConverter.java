package com.tad.submission.model.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tad.submission.exceptions.ConvertException;
import com.tad.submission.model.TestResult;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import static com.tad.submission.constants.message.CovertMessage.NOT_DESERIALIZED;
import static com.tad.submission.constants.message.CovertMessage.NOT_SERIALIZED;

@Converter(autoApply = true)
@Slf4j
public class TestResultConverter implements AttributeConverter<TestResult, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TestResult attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error(NOT_SERIALIZED, e);
            throw new ConvertException(NOT_SERIALIZED);
        }
    }

    @Override
    public TestResult convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, TestResult.class);
        } catch (JsonProcessingException e) {
            log.error(NOT_DESERIALIZED, e);
            throw new ConvertException(NOT_DESERIALIZED);
        }
    }
}
