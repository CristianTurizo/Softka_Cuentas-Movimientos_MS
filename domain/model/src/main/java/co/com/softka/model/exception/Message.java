package co.com.softka.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Message {

    ACCOUNT_NOT_FOUND("Persona no encontrada"),
    CLIENT_NOT_FOUND("Cliente no encontrado"),

    SAVE_ACCOUNT_ERROR("Error saving account"),
    FIND_ACCOUNT_ERROR("Error finding account"),
    DELETE_ACCOUNT_ERROR("Error deleting account"),

    ACCOUNT_CREATED_SUCCESSFULLY("Cuenta creada con exito"),
    ACCOUNT_UPDATED_SUCCESSFULLY("Cuenta actualizada con exito"),
    ACCOUNT_FOUND_SUCCESSFULLY("Cuenta encontrada con exito"),
    ACCOUNT_DELETED_SUCCESSFULLY("Cuenta eliminada con exito"),

    ;

    private final String message;
}
