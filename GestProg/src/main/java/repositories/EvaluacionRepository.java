package repositories;

import controllers.DataBaseManager;
import models.Evaluacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Iván Azagra Troya
 * Repositorio para las evaluaciones siguiendo el
 */

public class EvaluacionRepository implements IEvaluacionRepository {
    private static EvaluacionRepository instance;
    private final DataBaseManager db = DataBaseManager.getInstance();

    public EvaluacionRepository() {

    }

    /**
     * Patrón singleton
     * @return instancia de la clase
     */
    public static EvaluacionRepository getInstance() {
        if(instance == null) {
            instance = new EvaluacionRepository();
        }
        return instance;
    }

    /**
     * Busca evaluación por el nombre
     * @param nombre de la evaluación
     * @return Optional de la evaluación o null si no la encuentra
     * @throws SQLException Maneja los fallos con la base de datos
     */
    public Optional<Evaluacion> findByNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM evaluacion WHERE nombre = ?";
        db.open();
        ResultSet result = db.select(query, nombre).orElseThrow(() -> new SQLException("Error al realizar la consulta"));
        if(result.first()) {
            Evaluacion evaluacion = new Evaluacion(
                    result.getInt("idEvaluacion"),
                    result.getString("nombreEvaluacion"),
                    result.getFloat("notaMaxEvaluacion"),
                    result.getFloat("notaMinEvaluacion"),
                    result.getFloat("notaMediaEvaluacion"),
                    result.getInt("porcientoAprobados"),
                    result.getInt("porcientoSuspensos")
            );
            db.close();
            return Optional.of(evaluacion);
        }
        return Optional.empty();
    }

    /**
     * Busca evaluación por su id
     * @param id Número de identificación de la evaluación
     * @return la evaluación o null si no es encontrada
     * @throws SQLException Fallo con la base de datos
     */
    @Override
    public Optional<Evaluacion> findById(Integer id) throws SQLException {
        String query = "SELECT * FROM evaluacion WHERE id = ?";
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar la evaluacion con id: "+id));
        if(result.first()) {
            Evaluacion evaluacion = new Evaluacion(
                    result.getInt("idEvaluacion"),
                    result.getString("nombreEvaluacion"),
                    result.getFloat("notaMaxEvaluacion"),
                    result.getFloat("notaMinEvaluacion"),
                    result.getFloat("notaMediaEvaluacion"),
                    result.getInt("porcientoAprobados"),
                    result.getInt("porcientoSuspensos")
            );
            return Optional.of(evaluacion);
        }
        return Optional.empty();
    }

    /**
     * Devuelve todas las evaluaciones
     * @return lista de evaluaciones
     */
    @Override
    public List<Evaluacion> findAll() throws SQLException {
        String query = "SELECT * FROM evaluacion";
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al obtener todas las evaluaciones"));
        ArrayList<Evaluacion> list = new ArrayList<>();
        while(result.next()) {
            list.add(
                    new Evaluacion(
                            result.getInt("idEvaluacion"),
                            result.getString("nombreEvaluacion"),
                            result.getFloat("notaMaxEvaluacion"),
                            result.getFloat("notaMinEvaluacion"),
                            result.getFloat("notaMediaEvaluacion"),
                            result.getInt("porcientoAprobados"),
                            result.getInt("porcientoSuspensos")
                    )
            );
        }
        db.close();
        return list;
    }

    /**
     * Evaluación a guardar
     * @param evaluacion evaluación que guarde el método
     * @return Un optional con la evaluación o null si no la encuentra
     * @throws SQLException Maneja los fallos con la base de datos
     */

    @Override
    public Optional<Evaluacion> save(Evaluacion evaluacion) throws SQLException {
        String query = "INERT INTO evaluacion VALUES (null, ?, ?, ?, ?, ?, ?)";
        db.open();
        ResultSet res = db.insert(query, evaluacion.getNombreEvaluacion(), evaluacion.getNotaMaxEvaluacion(),
                evaluacion.getNotaMinEvaluacion(), evaluacion.getNotaMediaEvaluacion(), evaluacion.getPorcientoAprobados(),
                evaluacion.getPorcientoSuspensos()).orElseThrow(() -> new SQLException("Error al insertar la evaluación"));
        // Para obtener el id que ha generado la BD
        if(res.first()) {
            evaluacion.setIdEvaluacion(res.getInt(1));
            db.close();
            return Optional.of(evaluacion);
        }
        return Optional.empty();
    }

    /**
     *
     * @param id Número de identificación del Alumno
     * @return evaluación que ha sido eliminado o null si no lo encuentra
     * @throws SQLException Exceptión de fallo con la base de datos
     */

    @Override
    public Optional<Evaluacion> delete(Integer id) throws SQLException {
        Evaluacion evaluacion = this.findById(id).orElseThrow(() -> new SQLException("Error al eliminar la evaluación con id "+id+ ", no ha sido encontrado"));
        String query = "DELETE FROM evaluacion WHERE id = ?";
        db.open();
        db.delete(query, id);
        db.close();
        return Optional.of(evaluacion);
    }

    @Override
    public void clearAll() throws SQLException {
        String query = "DELETE FROM evaluacion";
        db.open();
        db.delete(query);
        db.close();
    }
}
