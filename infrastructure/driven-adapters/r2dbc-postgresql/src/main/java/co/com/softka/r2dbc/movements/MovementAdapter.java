package co.com.softka.r2dbc.movements;

import co.com.softka.model.exception.BussinesException;
import co.com.softka.model.exception.Message;
import co.com.softka.model.movements.Movement;
import co.com.softka.model.movements.gateways.MovementGateway;
import co.com.softka.r2dbc.helper.ReactiveAdapterOperations;
import co.com.softka.r2dbc.movements.entity.MovementEntity;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Slf4j
@Repository
public class MovementAdapter extends ReactiveAdapterOperations<Movement, MovementEntity, Integer, MovementRepository>
implements MovementGateway
{
    public MovementAdapter(MovementRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Movement.class));
    }

    @Override
    public Mono<Movement> saveMovement(Movement movement) {
        return this.save(movement)
                .doFirst(() -> log.info("Saving movement"))
                .doOnError(e -> log.error("Error saving movement -> {}", e.getMessage()))
                .onErrorMap(DataIntegrityViolationException.class, error -> new BussinesException(Message.SAVE_MOVEMENT_ERROR));
    }

    @Override
    public Mono<Movement> getMovementById(Integer id) {
        return this.findById(id)
                .doFirst(() -> log.info("Finding movement with id: {}", id))
                .doOnError(error -> log.error("Error finding movement with id: {} -> {}", id, error.getMessage()));
    }

    @Override
    public Flux<Movement> getMovementsByAccountIdAndDate(Integer accountId, LocalDate initDate, LocalDate finalDate) {
        return this.repository.findMovementsByAccountNumberAndDateBetween(accountId, initDate, finalDate)
                .map(this::toEntity)
                .doFirst(() -> log.info("Finding movement with accountId: {}", accountId))
                .doOnError(error -> log.error("Error finding movement with accountId: {} -> {}", accountId, error.getMessage()));
    }

}
