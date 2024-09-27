package co.com.softka.api;

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {RouterRest.class, AccountHandler.class})
@WebFluxTest
class RouterRestTest {

}
