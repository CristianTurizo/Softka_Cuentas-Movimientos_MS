package co.com.softka.api.dto.response;

public record GenericResponse<T>(
        T data,
        String message,
        String error) {
}
