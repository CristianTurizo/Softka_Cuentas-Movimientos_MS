package co.com.softka.r2dbc.account;

import co.com.softka.r2dbc.helper.ReactiveAdapterOperations;
import co.com.softka.r2dbc.movements.MovementRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountAdapter extends ReactiveAdapterOperations<Object/* change for domain model */, Object/* change for adapter model */, String, AccountRepository>
// implements ModelRepository from domain
{
    public AccountAdapter(AccountRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Object.class/* change for domain model */));
    }

}
