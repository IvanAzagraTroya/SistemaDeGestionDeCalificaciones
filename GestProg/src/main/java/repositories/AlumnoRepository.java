package repositories;

import models.Alumno;

import java.util.*;


/**
 * Repository para los alumnos siguiendo el TDA Mapa
 */
public class AlumnoRepository implements IAlumnoRepository {
    private final TreeMap<Integer, Alumno> alumnos = new TreeMap<>();

    /**
     * Busca un alumno por su nombre
     * @param nombre nombre del alumno
     * @return el alumno encontrado o null si no lo encuentra
     */
    public Alumno findByNombre(String nombre) {
        for (Alumno alumno : this.alumnos.values()) {
            if (alumno.getNombre().equals(nombre.toUpperCase()))
                return alumno;
        }
        return null;
    }

    /**
     * Busca un alumno por su id
     * @param id id del pais
     * @return el alumno encontrado o null si no lo encuentra
     */
    @Override
    public Optional<Alumno> findById(Integer id) {
        return Optional.ofNullable(this.alumnos.get(id));
    }


    /**
     * Devuelve todos los alumnos
     * @return lista de alumnos
     */
    @Override
    public List<Alumno> findAll() {
        return new ArrayList<>(this.alumnos.values());
    }

    /**
     * Añade un alumno
     * @param alumno alumno a añadir
     */
    @Override
    public Optional<Alumno> save(Alumno alumno) {
        this.alumnos.put(alumno.getId(), alumno);
        return Optional.of(alumno);
    }

    /**
     * Actualiza un alumno
     * @param id   id del alumno a actualizar
     * @param alumno alumno con los nuevos datos
     * @return el alumno actualizado
     */
    @Override
    public Optional<Alumno> update(Integer id, Alumno alumno) {
        this.alumnos.put(id, alumno);
        return Optional.of(alumno);
    }

    /**
     * Elimina un alumno dado el id
     *todo cambiarlo según las especificaciones del ejercicio
     * @param id id del alumno a eliminar
     * @return el alumno eliminado o null si no lo encuentra
     */
    @Override
    public Optional<Alumno> delete(Integer id) {

        return Optional.ofNullable(this.alumnos.remove(id));
    }

    @Override
    public void clearAll() {
        this.alumnos.clear();
    }

}