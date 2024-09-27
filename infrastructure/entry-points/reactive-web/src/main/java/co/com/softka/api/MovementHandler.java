package co.com.softka.api;

import co.com.softka.api.dto.request.MovementsDto;
import co.com.softka.api.dto.response.GenericResponse;
import co.com.softka.api.mapper.Mapper;
import co.com.softka.model.exception.Message;
import co.com.softka.model.movements.Movement;
import co.com.softka.usecase.account.AccountUseCase;
import co.com.softka.usecase.movements.MovementUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovementHandler {

    private final MovementUseCase movementUseCase;

    private final Mapper mapper;

    public Mono<ServerResponse> saveMovement(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MovementsDto.class)
                .map(this.mapper::toModel)
                .flatMap(this.movementUseCase::createMovement)
                .map(this.mapper::toDto)
                .flatMap(movementDto -> this.buildResponse(movementDto, Message.MOVEMENT_CREATED_SUCCESSFULLY, null))
                .doFirst(() -> log.info("Start saving movement"))
                .doOnError(error -> log.error(Message.SAVE_MOVEMENT_ERROR.getMessage().concat(" with exception: {}"), error.getMessage()))
                .onErrorResume(error -> this.buildResponse(null, Message.SAVE_MOVEMENT_ERROR, error.getMessage()));
    }

    public Mono<ServerResponse> getMovementById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("id"))
                .map(Integer::parseInt)
                .flatMap(this.movementUseCase::getMovementById)
                .map(this.mapper::toDto)
                .flatMap(movementDto -> this.buildResponse(movementDto, Message.MOVEMENT_FOUND_SUCCESSFULLY, null))
                .doFirst(() -> log.info("Start finding movement"))
                .doOnError(error -> log.error(Message.FIND_MOVEMENT_ERROR.getMessage().concat(" with exception: {}"), error.getMessage()))
                .onErrorResume(error -> this.buildResponse(null, Message.FIND_MOVEMENT_ERROR, error.getMessage()));
    }


    public Mono<ServerResponse> getReport(ServerRequest serverRequest) {
        String idCliente = serverRequest.queryParam("idCliente").get();
        String initialDate = serverRequest.queryParam("initialDate").get();
        String finalDate = serverRequest.queryParam("finalDate").get();

        return this.movementUseCase.getReport(idCliente, initialDate, finalDate)
                .map(this.mapper::toDto)
                .flatMap(movementDto -> this.buildResponse(movementDto, Message.REPORT_GENERATED_SUCCESSFULLY, null))
                .doFirst(() -> log.info("Start finding report"))
                .doOnError(error -> log.error(Message.REPORT_ERROR.getMessage().concat(" with exception: {}"), error.getMessage()))
                .onErrorResume(error -> this.buildResponse(null, Message.REPORT_ERROR, error.getMessage()));
    }

    private <T> Mono<ServerResponse> buildResponse(T data, Message responseMessage, String error) {
        GenericResponse<T> response = new GenericResponse<>(
                data,
                responseMessage.getMessage(),
                error);
        return ServerResponse.ok().bodyValue(response);
    }

}
