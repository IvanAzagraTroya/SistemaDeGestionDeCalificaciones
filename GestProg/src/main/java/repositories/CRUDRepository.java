package repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que permite realizar las tareas del CRUD
 * (CREATE, REMOVE, UPDATE, DELETE) de alumno,
 * en nuestro caso variamos UPDATE por ... todo
 * @param <T>
 * @param <ID>
 * @throws SQLException en caso de error en la base de datos
 */
public interface CRUDRepository<T, ID> {

    /**
     *
     * @return lista de elementos
     * @throws SQLException
     */
    List<T> findAll() throws SQLException;

    /**
     *
     * @param id
     * @return Optional del elemento
     * @throws SQLException
     */
    Optional<T> findById(ID id) throws SQLException;

    /**
     *
     * @param entity
     * @return Elemento insertado
     * @throws SQLException
     */
    Optional<T> save(T entity) throws SQLException;


    /**
     *
     * @param id
     * @return Elemento eliminado
     * @throws SQLException
     */
    Optional<T> delete(ID id) throws SQLException;
}
