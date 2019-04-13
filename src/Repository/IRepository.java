package Repository;


import Domain.Entity;

import java.util.List;

public interface IRepository <T extends Entity> {

    T findById(String id);

    //void upsert (T medicament);

    void insert(T entity);

    void update(T entity);

    void remove(String id);

    List<T> getAll();

}