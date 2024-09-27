package co.com.softka.model.movements.gateways;

import co.com.softka.model.movements.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface MovementGateway {
    Mono<Movement> saveMovement(Movement movement);
    Mono<Movement> getMovementById(Integer id);
    Flux<Movement> getMovementsByAccountIdAndDate(Integer accountId, LocalDate initDate, LocalDate finalDate);

}
