package repositories;

import models.Alumno;

// Toda nueva funcionalidad se extiende de la interfaz SOLID
public interface IAlumnoRepository extends CRUDRepository<Alumno, Integer>{
    Alumno findByNombre(String nombre);

    void clearAll();
}
