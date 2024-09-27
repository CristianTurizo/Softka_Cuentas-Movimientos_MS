package co.com.softka.usecase.movements;

import co.com.softka.model.account.Account;
import co.com.softka.model.exception.BussinesException;
import co.com.softka.model.exception.Message;
import co.com.softka.model.movements.Movement;
import co.com.softka.model.movements.gateways.MovementGateway;
import co.com.softka.usecase.account.AccountUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MovementUseCase {
    private final MovementGateway movementGateway;
    private final AccountUseCase accountUseCase;
    public static final String RETIRO = "retiro";
    public static final String DEPOSITO = "deposito";

    public Mono<Movement> createMovement(Movement movement) {
        movement.setDate(LocalDateTime.now().toString());

        return this.accountUseCase.getAcccountById(movement.getAccountNumber())
                .switchIfEmpty(Mono.error(new BussinesException(Message.ACCOUNT_NOT_FOUND)))
                .flatMap(this::validateAccountStatus)
                .flatMap(account -> this.updateBalance(account, movement))
                .flatMap(TupleUtils.function(this::saveNewBalaceInfo));
    }

    private Mono<Account> validateAccountStatus(Account account){
        if (account.getEstado().equals(Boolean.FALSE)){
            return Mono.error(new BussinesException(Message.INVALID_ACCOUNT));
        }
        return Mono.just(account);
    }

    private Mono<Tuple2<Account, Movement>> updateBalance(Account account, Movement movement) {
        if (movement.getAmount() == 0) {
            return Mono.error(new BussinesException(Message.INVALID_AMOUNT));
        }

        int newBalance = account.getBalance() + movement.getAmount();

        if (movement.getAmount() > 0) {
            movement.setType(DEPOSITO);
        } else {
            movement.setType(RETIRO);
            if (newBalance < 0) {
                return Mono.error(new BussinesException(Message.INSUFFICIENT_FUNDS));
            }
        }

        account.setBalance(newBalance);
        movement.setAccountBalance(newBalance);

        return Mono.zip(Mono.just(account), Mono.just(movement));
    }

    private Mono<Movement> saveNewBalaceInfo(Account account, Movement movement) {
        return this.accountUseCase.updateAccountBalance(account)
                .then(this.movementGateway.saveMovement(movement));
    }


    public Mono<Movement> getMovementById(Integer id) {
        return this.movementGateway.getMovementById(id)
                .switchIfEmpty(Mono.error(new BussinesException(Message.MOVEMENT_NOT_FOUND)));
    }

}
