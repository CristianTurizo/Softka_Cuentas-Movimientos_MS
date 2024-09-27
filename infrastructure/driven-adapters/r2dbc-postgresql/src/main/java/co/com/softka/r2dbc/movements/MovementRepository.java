package co.com.softka.r2dbc.movements;

import co.com.softka.r2dbc.movements.entity.MovementEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MovementRepository extends ReactiveCrudRepository<MovementEntity, Integer>, ReactiveQueryByExampleExecutor<MovementEntity> {
    Flux<MovementEntity> findMovementsByAccountNumber(Integer accountNumber);

}
