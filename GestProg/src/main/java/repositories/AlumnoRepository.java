package repositories;

import models.Alumno;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


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
            if (alumno.getNombre().equals(nombre))
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
    public Alumno findById(Integer id) {
        return this.alumnos.get(id);
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
    public Alumno save(Alumno alumno) {
        this.alumnos.put(alumno.getId(), alumno);
        return alumno;
    }

    /**
     * Actualiza un alumno
     * @param id   id del alumno a actualizar
     * @param alumno alumno con los nuevos datos
     * @return el alumno actualizado
     */
    @Override
    public Alumno update(Integer id, Alumno alumno) {
        this.alumnos.put(id, alumno);
        return alumno;
    }

    /**
     * Elimina un alumno dado el id
     *todo cambiarlo según las especificaciones del ejercicio
     * @param id id del alumno a eliminar
     * @return el alumno eliminado o null si no lo encuentra
     */
    @Override
    public Alumno delete(Integer id) {

        return this.alumnos.remove(id);
    }

    @Override
    public void clearAll() {
        this.alumnos.clear();
    }

}