package com.latif.vidiojavaspringboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.exception.InvalidTokenException;
import com.latif.vidiojavaspringboot.util.JWTGenerator;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // Extract the JWT token from the request header
        String token = request.getHeader("token");
        if (token == null) {
            // If token is missing, return a 403 Forbidden response
            ResMessageDto<String> body = new ResMessageDto<>(
                    HttpStatus.FORBIDDEN.value(), "You don't have permission", null
            );
            internalServerError(body, response);
            return false;
        }
        try {
            // Attempt to decode the JWT token to check its validity
            new JWTGenerator().decodeJwt(token).get("sub");
        } catch (ExpiredJwtException e) {
            // If the token is expired, return a 401 Unauthorized response
            e.printStackTrace();
            ResMessageDto<String> body = new ResMessageDto<>(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Invalid token",
                    null
            );
            internalServerError(body, response);
            return false;
        } catch (InvalidTokenException e) {
            // If the token is invalid or malformed, return a 401 Unauthorized response
            e.printStackTrace();
            ResMessageDto<String> body = new ResMessageDto<>(
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage() != null ? e.getMessage() : "Invalid token",
                    null
            );
            internalServerError(body, response);
            return false;
        }
        // If token is valid and not expired, allow the request to proceed
        return true;
    }

    // Helper method to send internal server error response with JSON body
    private void internalServerError(ResMessageDto<String> body, HttpServletResponse response) throws IOException {
        response.setStatus(body.getStatus());
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(body));
    }

    // Helper method to convert DTO object to JSON string
    private String convertObjectToJson(ResMessageDto<String> dto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dto);
    }

}
