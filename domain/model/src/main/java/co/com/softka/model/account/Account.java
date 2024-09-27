package co.com.softka.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Account {

    private Integer accountNumber;
    private String tipoCuenta;
    private Integer balance;
    private Boolean estado;
    private Integer idCliente;
}
