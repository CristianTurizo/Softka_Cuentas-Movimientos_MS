package co.com.softka.model.report;

import co.com.softka.model.account.Account;
import co.com.softka.model.movements.Movement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Report extends Account {
    private List<Movement> movementList;

    public static Report fromAccount(Account account, List<Movement> movementList) {
        return Report.builder()
                .accountNumber(account.getAccountNumber())
                .tipoCuenta(account.getTipoCuenta())
                .balance(account.getBalance())
                .estado(account.getEstado())
                .idCliente(account.getIdCliente())
                .movementList(movementList)
                .build();
    }
}
