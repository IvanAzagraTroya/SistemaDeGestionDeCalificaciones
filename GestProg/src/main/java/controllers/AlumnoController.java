package controllers;

import exceptions.AlumnoException;
import models.Alumno;
import repositories.AlumnoRepository;
import repositories.IAlumnoRepository;
import services.IStorageAlumno;
import services.StorageAlumnosCSVFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Controlador de Gestion de Alumnos
 * Dependencias: AlumnoRepository
 */
public class AlumnoController {

    // private static PaisController instance;
    private final IAlumnoRepository alumnoRepository;
    private final IStorageAlumno alumnoStorage;

    // Inyección de dependencias por controlador
    public AlumnoController(IAlumnoRepository alumnoRepository, IStorageAlumno alumnoStorage) throws SQLException {
        this.alumnoRepository = alumnoRepository;
        this.alumnoStorage = alumnoStorage;
        loadData();
    }


    /**
     * Inicializa el repositorio con datos de prueba
     */
    private void loadData() throws SQLException {

        this.alumnoRepository.save(new Alumno("Francisco", "Toribio Respaldo", "53415788J", "111-666666", true));
        this.alumnoRepository.save(new Alumno("Iván", "Azagra Troya", "53415788J", "111-666666", true));

    }

    public Optional<Alumno> crearAlumno(Alumno alumno) throws AlumnoException, SQLException {
        // Comprobamos que no haya datos incorrectos, por si ha fallado la interfaz
        checkAlumnoData(alumno);
        // comprobamos si existe
        var existe = alumnoRepository.findByNombre(alumno.getNombre());
        if (existe == null) {
            alumnoRepository.save(alumno);
            return Optional.of(alumno);

        }
        throw new AlumnoException("Ya existe un alumno con el nombre " + alumno.getNombre());
    }

    /**
     * Comprueba que los datos del alumno son correctos.
     * No lo hacemos en el modelo para no contaminarlo.
     *
     * @param alumno Alumno a comprobar
     * @throws AlumnoException Si los datos son incorrectos
     */
    private void checkAlumnoData(Alumno alumno) throws AlumnoException {
        if (alumno.getNombre() == null || alumno.getNombre().trim().isEmpty()) {
            throw new AlumnoException("El nombre del alumno no puede estar vacío");
        }

        if (alumno.getApellidos() == null || alumno.getApellidos().trim().isEmpty()) {
            throw new AlumnoException("Los apellidos del alumno no pueden estar vacíos");
        }
        if (alumno.getDni() == null || alumno.getDni().trim().isEmpty()) {
            throw new AlumnoException("El DNI del alumno no puede estar vacío");
        }
        if (alumno.getTelefono() == null || alumno.getTelefono().trim().isEmpty()) {
            throw new AlumnoException("El teléfono  no puede estar vacío");
        }
        if (alumno.getEvaluacionContinua() == null) {
            throw new AlumnoException("La evaluación continua debe ser true o false");
        }
    }

    /**
     * Devuelve el alumno con el nombre indicado
     *
     * @param nombre nombre del alumno
     * @return Alumno con el nombre indicado
     * @throws AlumnoException si no existe el pais
     */
    public Optional<Alumno> getAlumnoByNombre(String nombre) throws AlumnoException {
        var alumno = alumnoRepository.findByNombre(nombre);
        if (alumno != null) {
            return alumno;
        }
        throw new AlumnoException("No existe el alumno con nombre " + nombre);
    }

    /**
     * Devuelve el alumno con el id indicado
     *
     * @param id id del alumno
     * @return Alumno con el id indicado
     * @throws AlumnoException si no existe el pais
     */
    public Optional<Alumno> getAlumnoById(int id) throws AlumnoException, SQLException {
        var alumno = alumnoRepository.findById(id);
        if (alumno != null) {
            return alumno;
        }
        throw new AlumnoException("No existe el pais con id " + id);
    }

    /**
     * Devuelve todos los alumnos
     * @return Lista de alumnos
     */
    public List<Alumno> getAllAlumnos() throws SQLException {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> updateAlumno(int id, Alumno alumno) throws AlumnoException, SQLException {
        // Comprobamos los datos
        checkAlumnoData(alumno);
        // Vemos si con los datos nuevos existe un alumno que se llame igual
        var otro = alumnoRepository.findByNombre(alumno.getNombre());
        // si no existe otro alumno con el mismo nombre, actualizamos
        if ((otro == null) || (otro.getId() == alumno.getId())) {
            // si no existe otro pais con el mismo nombre, lo actualizamos o somos nosotros por id
            alumnoRepository.update(id, alumno);
            return Optional.of(alumno);
        } else {
            throw new AlumnoException("Ya existe un alumno con el nombre " + alumno.getNombre());
        }
    }

    /**
     * Elimina el alumno con el nombre indicado
     * @param nombre nombre del alumno
     * @return Alumno eliminado
     * @throws AlumnoException si no existe el alumno
     */
    public Optional<Alumno> deleteAlumno(String nombre) throws AlumnoException, SQLException {
        var alumno = alumnoRepository.findByNombre(nombre);
        if (alumno != null) {
            // alumnoRepository.deleteById(alumno.getId());
            alumnoRepository.delete(alumno.getId());
            return alumno;
        } else {
            throw new AlumnoException("No existe el alumno con nombre " + nombre);
        }
    }

    /**
     * Importa los datos desde un fichero de backup
     */
    public void importarDatos() throws SQLException {
        System.out.println("Importando datos de alumnos...");
        System.out.println("Importando desde: " + alumnoStorage.getStoragePath());
        var lista = alumnoStorage.load();
        if (lista.size() > 0) {
            alumnoRepository.clearAll();
            for (Alumno pais : lista) {
                // pais
                alumnoRepository.save(pais);
            }
            System.out.println("Datos importados con éxito al repositorio: " + lista.size() + " alumnos");
        } else {
            System.out.println("Ha existido un problema al importar los datos");
        }
    }

    /**
     * Exporta los datos desde un fichero de Backup
     */
    public void exportarDatos() throws SQLException {
        System.out.println("Exportando datos de alumnos...");
        var res = alumnoStorage.save(alumnoRepository.findAll());
        if (res) {
            System.out.println("Datos exportados con éxito en: " + alumnoStorage.getStoragePath());
        } else {
            System.out.println("Ha existido un problema al exportar los datos");
        }
    }
}