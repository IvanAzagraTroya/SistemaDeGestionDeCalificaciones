package repositories;

import controllers.DataBaseManager;
import models.Alumno;
import org.apache.ibatis.jdbc.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * Repository para los alumnos siguiendo el TDA Mapa
 */
public class AlumnoRepository implements IAlumnoRepository {
    private static AlumnoRepository instance;
    DataBaseManager db = DataBaseManager.getInstance();

    private AlumnoRepository(DataBaseManager dataBaseManager) {
        this.db = dataBaseManager;
    }

    public static AlumnoRepository getInstance(DataBaseManager dataBaseManager) {
        if(instance == null) {
            instance = new AlumnoRepository(dataBaseManager);
        }
        return instance;
    }
    /**
     * Busca un alumno por su nombre
     * @param nombre nombre del alumno
     * @return el alumno encontrado o null si no lo encuentra
     */
    public Optional<Alumno> findByNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM alumno WHERE nombre = ?";
        db.open();
        ResultSet result = db.select(query, nombre).orElseThrow(() -> new SQLException("Error al consultar alumno con nombre: "+ nombre));
        if (result.first()) {
            Alumno alumno = new Alumno(
                    result.getInt("id"),
                    result.getString("nombre"),
                    result.getString("apellidos"),
                    result.getString("dni"),
                    result.getString("telefono"),
                    result.getString("evaluacionContinua")
            );
            db.close();
            return Optional.of(alumno);
        }
        return Optional.empty();
    }

    /**
     * Busca un alumno por su id
     * @param id id del pais
     * @return el alumno encontrado o null si no lo encuentra
     */
    @Override
    public Optional<Alumno> findById(Integer id) throws SQLException {
        String query = "SELECT * FROM alumno WHERE id = ?";
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar el id: "+id));
        if(result.first()) {
            Alumno alumno = new Alumno(
                    result.getInt("id"),
                    result.getString("nombre"),
                    result.getString("apellidos"),
                    result.getString("dni"),
                    result.getString("telefono"),
                    result.getString("evaluacionContinua")
            );
            db.close();
            return Optional.of(alumno);
        }
        return Optional.empty();
    }


    /**
     * Devuelve todos los alumnos
     * @return lista de alumnos
     */
    @Override
    public List<Alumno> findAll() throws SQLException {
        String query = "SELECT * FROM alumno";
        List<Alumno> alumnos = new ArrayList<>();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al obtener todos los alumnos"));

        int posicion = 1;
        while(result.next()){
            alumnos.add(result.getObject(posicion,Alumno.class));
            posicion++;
        }

        return alumnos;
    }
    /**
     * Añade un alumno
     * @param alumno alumno a añadir
     */
    @Override
    public Optional<Alumno> save(Alumno alumno) {
        String query = "insert into alumno (nombre,apellidos,dni,telefono,evaluacion_continua) values(?,?,?,?,?)";
        Alumno a = new Alumno();
        try{
            db.open();
            Optional<ResultSet> rs = db.insert(
                    query,
                    alumno.getNombre(),
                    alumno.getApellidos(),
                    alumno.getDni(),
                    alumno.getTelefono(),
                    alumno.getEvaluacionContinua()
            );

            if(rs.isPresent()){
                a = rs.get().getObject(1,Alumno.class); // Si no funciona cambiar a casteo normal y quitar el segundo parámetro
            }else{
                throw new Exception("Error al recibir el alumno creado");
            }
            db.close();
        }catch(SQLException e){
            System.err.println("No se ha podido crear el usuario");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(a);
    }

    /**
     * Actualiza un alumno
     * @param id   id del alumno a actualizar
     * @param alumno alumno con los nuevos datos
     * @return el alumno actualizado
     */
    @Override
    public Optional<Alumno> update(Integer id, Alumno alumno) throws SQLException {
        this.findById(id).orElseThrow(() -> new SQLException("Error al actualizar el alumno con id: " +id));
        String query = "UPDATE alumno SET nombre = ?, apellidos = ?, dni = ?, telefono = ?, evaluacionContinua = ?" +
                "WHERE id = ?";
        db.open();
        int res = db.update(query, alumno.getNombre(), alumno.getApellidos(),alumno.getTelefono(),
                alumno.getEvaluacionContinua());
        db.close();
        return Optional.of(alumno);
    }

    /**
     * Elimina un alumno dado el id
     *todo cambiarlo según las especificaciones del ejercicio
     * @param id id del alumno a eliminar
     * @return el alumno eliminado o null si no lo encuentra
     */
    @Override
    public Optional<Alumno> delete(Integer id) throws SQLException {
        Alumno alumno = this.findById(id).orElseThrow(() -> new SQLException("Error al borrar el alumno con id: "+id));
        String query = "DELETE FROM alumno WHERE id = ?";
        db.open();
        db.delete(query, id);
        db.close();
        return Optional.of(alumno);
    }

    @Override
    public void clearAll() throws SQLException {
        String query = "DELETE FROM alumno";
        db.open();
        db.delete(query);
        db.close();
    }

}