package co.com.softka.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account {

    private Integer accountNumber;
    private String tipoCuenta;
    private Integer balance;
    private Boolean estado;
    private Integer idCliente;
}
