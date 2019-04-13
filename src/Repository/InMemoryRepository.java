package Repository;

import Domain.ClientValExc;
import Domain.Entity;
import Domain.IValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository <T extends Entity> implements IRepository<T> {

    private Map<String,T> storage = new HashMap<>();
    private IValidator<T> validator;

    public InMemoryRepository(IValidator<T> validator) {
        this.validator = validator;
    }

    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public void insert(T entity) {

        if (storage.containsKey(entity.getId())) {
            throw new RepoExc("An entity with  this id already exists");
        }
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }

    @Override
    public void update(T entity) {
        if (!storage.containsKey(entity.getId())) {
            throw new RepoExc("A entity with id doesn't exists!");
        }
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }

    /*public void upsert(T entity) {
            validator.validate(entity);
            storage.put(entity.getId(), entity);
    }*/

    public void remove(String id){
        if(!storage.containsKey(id)){
            throw new RepoExc("There is no entity with the given id to remove.");
        }

        storage.remove(id);
    }

    public List<T> getAll() {
        return  new ArrayList<>(storage.values());
    }
}