package co.com.softka.r2dbc.movements;

import co.com.softka.model.movements.Movement;
import co.com.softka.model.movements.gateways.MovementsGateway;
import co.com.softka.r2dbc.helper.ReactiveAdapterOperations;
import co.com.softka.r2dbc.movements.entity.MovementEntity;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MovementAdapter extends ReactiveAdapterOperations<Movement, MovementEntity, Integer, MovementRepository>
implements MovementsGateway
{
    public MovementAdapter(MovementRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Movement.class));
    }

}
