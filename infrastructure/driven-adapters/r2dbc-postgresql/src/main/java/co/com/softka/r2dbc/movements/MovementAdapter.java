package co.com.softka.r2dbc.movements;

import co.com.softka.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MovementAdapter extends ReactiveAdapterOperations<Object/* change for domain model */, Object/* change for adapter model */, String, MovementRepository>
// implements ModelRepository from domain
{
    public MovementAdapter(MovementRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Object.class/* change for domain model */));
    }

}
