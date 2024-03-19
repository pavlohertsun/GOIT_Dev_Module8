package repositories;

import java.util.List;

public interface CrudRepository <T> {
    List<T> listAll();
    T getById(long id);
    long create(T entity);
    void update(T entity);
    void delete(T entity);
}
