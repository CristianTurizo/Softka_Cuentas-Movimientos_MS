package co.com.softka.model.movements.gateways;

import co.com.softka.model.movements.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementGateway {
    Mono<Movement> saveMovement(Movement movement);
    Mono<Movement> getMovementById(Integer id);
    Flux<Movement> getMovementsByAccountId(Integer accountId);

}
