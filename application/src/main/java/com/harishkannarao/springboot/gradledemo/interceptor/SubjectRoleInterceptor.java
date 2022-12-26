package com.harishkannarao.springboot.gradledemo.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SubjectRoleInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Optional<RolesAllowed> rolesAllowed = Optional.ofNullable(method.getMethodAnnotation(RolesAllowed.class));
            rolesAllowed.ifPresent(allowed -> {
                Objects.requireNonNull(allowed.value());
                List<String> permittedRoles = Arrays.stream(allowed.value()).collect(Collectors.toList());
                String requestRole = Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required")
                        );
                if (!permittedRoles.contains(requestRole)) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Operation not permitted");
                }
            });
        }
        return true;
    }
}