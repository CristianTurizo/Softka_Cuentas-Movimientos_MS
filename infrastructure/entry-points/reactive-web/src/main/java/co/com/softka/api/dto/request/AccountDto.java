package co.com.softka.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class AccountDto {
    private Integer accountNumber;
    private String tipoCuenta;
    private Integer balance;
    private Boolean estado;
    private Integer idCliente;

}
