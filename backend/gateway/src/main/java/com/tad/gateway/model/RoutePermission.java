package com.tad.gateway.model;

import com.tad.gateway.constants.ROLE;
import com.tad.gateway.constants.HttpMethod;

import java.util.List;

public record RoutePermission(
        HttpMethod method,
        String pathPattern,
        List<ROLE> roles
) {}
