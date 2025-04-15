package com.tad.submission.mapper;

import com.tad.submission.dto.response.GetDetailResponse;
import com.tad.submission.dto.response.GetSubmissionsResponse;
import org.springframework.http.ResponseEntity;

public class HttpResponseMapper {

    private HttpResponseMapper() {
        super();
    };

    public static ResponseEntity<GetDetailResponse> toHttpResponse(GetDetailResponse response) {
        return new ResponseEntity(
                response.detail(),
                response.status());
    }

    public static ResponseEntity<GetSubmissionsResponse> toHttpResponse(GetSubmissionsResponse response) {
        return new ResponseEntity(
                response.submissions(),
                response.status());
    }
}
