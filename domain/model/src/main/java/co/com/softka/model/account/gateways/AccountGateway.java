package co.com.softka.model.account.gateways;

import co.com.softka.model.account.Account;
import reactor.core.publisher.Mono;

public interface AccountGateway {
    Mono<Account> saveAccount(Account account);
    Mono<Account> getAccountById(Integer id);
    Mono<Account> getAccountByIdClient(Integer id);
    Mono<Void> deteleAccountById(Integer id);

}
