package repositories;

import models.Alumno;

import java.sql.SQLException;
import java.util.Optional;

// Toda nueva funcionalidad se extiende de la interfaz SOLID
public interface IAlumnoRepository extends CRUDRepository<Alumno, Integer>{
    Optional<Alumno>findByNombre(String nombre);

    Optional<Alumno> update(Integer id, Alumno alumno);

    void clearAll();

    /**
     *
     * @param id
     * @param alumno
     * @return Elemento actualizado
     * @throws SQLException
     */
    Optional<Alumno> update(int id, Alumno alumno) throws SQLException;
}
