package ORM;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbContext<E> {
    boolean persist(E entity) throws IllegalAccessException, ClassNotFoundException, SQLException;

    Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException;

    Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException;

    E findFirst(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException;

    E findFirst(Class<E> table,String where) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException;

    void doDelete(Class<?> table,String condition) throws Exception;

}
