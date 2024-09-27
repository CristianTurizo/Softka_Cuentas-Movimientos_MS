package co.com.softka.usecase.account;

import co.com.softka.model.account.Account;
import co.com.softka.model.account.gateways.AccountGateway;
import co.com.softka.model.exception.BussinesException;
import co.com.softka.model.exception.Message;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AccountUseCase {

    private final AccountGateway accountGateway;

    public Mono<Account> createAccount(Account account) {
        account.setEstado(true);
        return this.accountGateway.saveAccount(account);
    }

    public Mono<Void> updateAccount(Account account) {
        return this.accountGateway.getAccountById(account.getAccountNumber())
                .switchIfEmpty(Mono.error(new BussinesException(Message.ACCOUNT_NOT_FOUND)))
                .doOnNext(existedAccount -> existedAccount.setEstado(account.getEstado()))
                .flatMap(this.accountGateway::saveAccount)
                .then();
    }

    public Mono<Account> getAcccountById(Integer id) {
        return this.accountGateway.getAccountById(id)
                .switchIfEmpty(Mono.error(new BussinesException(Message.ACCOUNT_NOT_FOUND)));
    }

    public Mono<Void> deleteAccountById(Integer id) {
        return this.accountGateway.getAccountById(id)
                .switchIfEmpty(Mono.error(new BussinesException(Message.ACCOUNT_NOT_FOUND)))
                .then(this.accountGateway.deteleAccountById(id));
    }

    public Mono<Void> updateAccountBalance(Account account) {
        return this.accountGateway.saveAccount(account)
                .then();
    }
}
