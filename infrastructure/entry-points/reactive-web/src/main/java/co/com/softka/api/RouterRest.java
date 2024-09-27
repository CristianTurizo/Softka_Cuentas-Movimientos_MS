package co.com.softka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    private static final String ACCOUNT_PATH = "/cuentas";
    private static final String MOVEMENT_PATH = "/movimientos";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(AccountHandler accountHandler, MovementHandler movementHandler) {
        return route(GET(ACCOUNT_PATH.concat("/{id}")), accountHandler::getAccountById)
                .andRoute(POST(ACCOUNT_PATH), accountHandler::saveAccount)
                .andRoute(PUT(ACCOUNT_PATH), accountHandler::updateAccount)
                .andRoute(DELETE(ACCOUNT_PATH.concat("/{id}")), accountHandler::deleteAccountById)
                .andRoute(GET(MOVEMENT_PATH.concat("/{id}")), movementHandler::getMovementById)
                .andRoute(POST(MOVEMENT_PATH), movementHandler::saveMovement);
    }
}
