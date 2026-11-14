/*
 * Class Name  : ApiErrorResponseDto
 * Description : Represents a standardized API error response
 *               containing timestamp, status, error, message,
 *               request path and validation errors.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

package com.fundoo.notes.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiErrorResponseDto {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors;

    public ApiErrorResponseDto() {
    }

    public ApiErrorResponseDto(
            LocalDateTime timestamp,
            int status,
            String error,
            String message,
            String path,
            Map<String, String> validationErrors) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.validationErrors = validationErrors;
    }

    // [Asher Mustafa]: Returns the error timestamp.
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // [Asher Mustafa]: Returns the HTTP status code.
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // [Asher Mustafa]: Returns the HTTP error description.
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // [Asher Mustafa]: Returns the detailed error message.
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // [Asher Mustafa]: Returns the requested API path.
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // [Asher Mustafa]: Returns field-level validation errors, if any.
    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}