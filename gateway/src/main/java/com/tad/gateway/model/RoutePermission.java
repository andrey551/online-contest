package com.tad.gateway.model;

import com.tad.gateway.constants.ROLE;

import java.util.List;

public record RoutePermission(
        com.tad.gateway.constants.HttpMethod method,
        String pathPattern,
        List<ROLE> roles
) {}
