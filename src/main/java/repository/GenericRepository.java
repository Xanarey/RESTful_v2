package repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T insert(T t);
    List<T> getAll();
    T getById(Long id);
    void deleteById(ID id);
    T update(T t);
}
