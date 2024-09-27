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

    private static final String PATH = "/cuentas";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(AccountHandler handler) {
        return route(GET(PATH.concat("/{id}")), handler::getAccountById)
                .andRoute(POST(PATH), handler::saveAccount)
                .andRoute(PUT(PATH), handler::updateAccount)
                .andRoute(DELETE(PATH.concat("/{id}")), handler::deleteAccountById);
    }
}
