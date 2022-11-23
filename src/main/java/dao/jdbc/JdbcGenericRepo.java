package dao.jdbc;

import java.util.List;

public interface JdbcGenericRepo<T, ID> {
    T getById(ID id);
    List<T> getAll();
}
