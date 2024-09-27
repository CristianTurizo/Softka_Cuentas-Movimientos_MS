package co.com.softka.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Message {

    ACCOUNT_NOT_FOUND("Cuenta no encontrada"),
    CLIENT_NOT_FOUND("Cliente no existe"),
    MOVEMENT_NOT_FOUND("Movimiento no existe"),
    INVALID_AMOUNT("El saldo debe ser mayor que 0"),
    INVALID_ACCOUNT("Cuenta Invalida"),
    INSUFFICIENT_FUNDS("Fondos insuficientes"),

    SAVE_ACCOUNT_ERROR("Error saving account"),
    FIND_ACCOUNT_ERROR("Error finding account"),
    DELETE_ACCOUNT_ERROR("Error deleting account"),

    SAVE_MOVEMENT_ERROR("Error al guardar movimiento"),
    FIND_MOVEMENT_ERROR("Error al encontrar movimiento"),
    DELETE_MOVEMENT_ERROR("Error al eliminar movimiento"),


    ACCOUNT_CREATED_SUCCESSFULLY("Cuenta creada con exito"),
    ACCOUNT_UPDATED_SUCCESSFULLY("Cuenta actualizada con exito"),
    ACCOUNT_FOUND_SUCCESSFULLY("Cuenta encontrada con exito"),
    ACCOUNT_DELETED_SUCCESSFULLY("Cuenta eliminada con exito"),

    MOVEMENT_CREATED_SUCCESSFULLY("Movimiento creado con exito"),
    MOVEMENT_FOUND_SUCCESSFULLY("Movimiento encontrado con exito"),

    ;

    private final String message;
}
