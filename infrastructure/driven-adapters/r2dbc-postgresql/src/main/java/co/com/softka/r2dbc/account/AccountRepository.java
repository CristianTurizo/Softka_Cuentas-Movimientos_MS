package co.com.softka.r2dbc.account;

import co.com.softka.r2dbc.account.entity.AccountEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Integer>, ReactiveQueryByExampleExecutor<AccountEntity> {
    Mono<AccountEntity> findByIdCliente(Integer idCliente);


}
