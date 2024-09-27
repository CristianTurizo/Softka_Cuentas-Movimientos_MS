package co.com.softka.r2dbc.account;

import co.com.softka.model.account.Account;
import co.com.softka.model.account.gateways.AccountGateway;
import co.com.softka.model.exception.BussinesException;
import co.com.softka.model.exception.Message;
import co.com.softka.r2dbc.account.entity.AccountEntity;
import co.com.softka.r2dbc.helper.ReactiveAdapterOperations;
import co.com.softka.r2dbc.movements.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class AccountAdapter extends ReactiveAdapterOperations<Account, AccountEntity, Integer, AccountRepository>
implements AccountGateway
{
    public AccountAdapter(AccountRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Account.class));
    }

    @Override
    public Mono<Account> saveAccount(Account account) {
        return this.save(account)
                .doFirst(() -> log.info("Saving account"))
                .doOnError(e -> log.error("Error saving account -> {}", e.getMessage()))
                .onErrorMap(DataIntegrityViolationException.class, error-> new BussinesException(Message.CLIENT_NOT_FOUND));
    }

    @Override
    public Mono<Account> getAccountById(Integer id) {
        return this.findById(id)
                .doFirst(() -> log.info("Finding account with id: {}", id))
                .doOnError(error -> log.error("Error finding account with id: {} -> {}", id, error.getMessage()));
    }

    @Override
    public Mono<Account> getAccountByIdClient(Integer idCliente) {
        return this.repository.findByIdCliente(idCliente)
                .map(this::toEntity)
                .doFirst(() -> log.info("Finding account with idPersona: {}", idCliente))
                .doOnError(error -> log.error("Error finding account with idCliente: {} -> {}", idCliente, error.getMessage()));
    }

    @Override
    public Mono<Void> deteleAccountById(Integer id) {
        return this.repository.deleteById(id)
                .doFirst(() -> log.info("Deleting account with id: {}", id))
                .doOnError(error -> log.error("Error deleting account with id: {} -> {}", id, error.getMessage()));
    }
}
