package repositories;

import java.util.List;

/**
 * Interfaz que permite realizar las tareas del CRUD
 * (CREATE, REMOVE, UPDATE, DELETE) de alumno,
 * en nuestro caso variamos UPDATE por ... todo
 * @param <T>
 * @param <ID>
 */
public interface CRUDRepository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    T update(ID id, T entity);

    T delete(ID id);
}
