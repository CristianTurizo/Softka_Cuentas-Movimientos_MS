package co.com.softka.api;

import co.com.softka.api.dto.request.AccountDto;
import co.com.softka.api.dto.response.GenericResponse;
import co.com.softka.api.mapper.Mapper;
import co.com.softka.model.exception.Message;
import co.com.softka.usecase.account.AccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountHandler {
    private final AccountUseCase accountUseCase;

    private final Mapper mapper;

    public Mono<ServerResponse> saveAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AccountDto.class)
                .map(this.mapper::toModel)
                .flatMap(this.accountUseCase::createAccount)
                .map(this.mapper::toDto)
                .flatMap(accountDto -> this.buildResponse(accountDto, Message.ACCOUNT_CREATED_SUCCESSFULLY, null))
                .doFirst(() -> log.info("Start saving account"))
                .doOnError(error -> log.error(Message.SAVE_ACCOUNT_ERROR.getMessage().concat(" with exception: {}"), error.getMessage()))
                .onErrorResume(error -> this.buildResponse(null, Message.SAVE_ACCOUNT_ERROR, error.getMessage()));
    }

    public Mono<ServerResponse> updateAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AccountDto.class)
                .map(this.mapper::toModel)
                .flatMap(this.accountUseCase::updateAccount)
                .then(this.buildResponse(null, Message.ACCOUNT_UPDATED_SUCCESSFULLY, null))
                .doFirst(() -> log.info("Start updating account"))
                .doOnError(error -> log.error(Message.SAVE_ACCOUNT_ERROR.getMessage().concat(" with exception: {}"), error.getMessage()))
                .onErrorResume(error -> this.buildResponse(null, Message.SAVE_ACCOUNT_ERROR, error.getMessage()));
    }


    public Mono<ServerResponse> getAccountById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("id"))
                .map(Integer::parseInt)
                .flatMap(this.accountUseCase::getAcccountById)
                .map(this.mapper::toDto)
                .flatMap(accountDto -> this.buildResponse(accountDto, Message.ACCOUNT_FOUND_SUCCESSFULLY, null))
                .doFirst(() -> log.info("Start finding account"))
                .doOnError(error -> log.error(Message.FIND_ACCOUNT_ERROR.getMessage().concat(" with exception: {}"), error.getMessage()))
                .onErrorResume(error -> this.buildResponse(null, Message.FIND_ACCOUNT_ERROR, error.getMessage()));
    }

    public Mono<ServerResponse> deleteAccountById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("id"))
                .map(Integer::parseInt)
                .flatMap(this.accountUseCase::deleteAccountById)
                .then(this.buildResponse(null, Message.ACCOUNT_DELETED_SUCCESSFULLY, null))
                .doFirst(() -> log.info("Start deleting account"))
                .doOnError(error -> log.error(Message.DELETE_ACCOUNT_ERROR.getMessage().concat(" with exception: {}"), error.getMessage()))
                .onErrorResume(error -> this.buildResponse(null, Message.DELETE_ACCOUNT_ERROR, error.getMessage()));
    }

    private <T> Mono<ServerResponse> buildResponse(T data, Message responseMessage, String error) {
        GenericResponse<T> response = new GenericResponse<>(
                data,
                responseMessage.getMessage(),
                error);
        return ServerResponse.ok().bodyValue(response);
    }
}
