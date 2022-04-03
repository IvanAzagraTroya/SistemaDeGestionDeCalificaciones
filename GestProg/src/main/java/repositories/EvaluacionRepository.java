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
// Da error por no implementar la función update, pero por requisito de la aplicación la evaluación no puede ser actualizada
// una vez ha sido creada.
public class EvaluacionRepository implements IEvaluacionRepository {
    private static EvaluacionRepository instance;
    private final DataBaseManager db;

    private EvaluacionRepository(DataBaseManager db) {
        this.db = db;
    }

    /**
     * Patrón singleton
     * @return instancia de la clase
     */
    public static EvaluacionRepository getInstance(DataBaseManager db) {
        if(instance == null) {
            instance = new EvaluacionRepository(db);
        }
        return instance;
    }

    /**
     * Busca evaluación por el nombre
     * @params nombre
     * @throws SQLException+
     */
    public Optional<Evaluacion> findByNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM evaluacion WHERE nombre = ?";
        db.open();
        ResultSet result = db.select(query, nombre).orElseThrow(() -> new SQLException("Error al realizar la consulta"));
        if(result.first()) {
            // REVISAR ESTA PARTE YA QUE PODRÍA PETAR SI NO SE PARSEAN A PARTIR DEL TERCER ATRIBUTO A INT.
            Evaluacion evaluacion = new Evaluacion(
                    result.getInt("idEvaluacion"),
                    result.getString("nombreEvaluacion"),
                    result.getString("notaMaxEvaluacion"),
                    result.getString("notaMinEvaluacion"),
                    result.getString("notaMediaEvaluacion"),
                    result.getString("porcientoAprobados"),
                    result.getString("porcientoSuspensos")
            );
            db.close();
            return Optional.of(evaluacion);
        }
        return Optional.empty();
    }

    /**
     * Busca evaluación por su id
     * @param id
     * @return la evaluación o null si no es encontrada
     * @throws SQLException
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
                    result.getString("notaMaxEvaluacion"),
                    result.getString("notaMinEvaluacion"),
                    result.getString("notaMediaEvaluacion"),
                    result.getString("porcientoAprobados"),
                    result.getString("porcientoSuspensos")
            );
            return Optional.of(evaluacion);
        }
        return Optional.empty();
    }

    /**
     * Devuelve todas las evaluaciones
     * @reutrn lista de evaluaciones
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
                            result.getString("notaMaxEvaluacion"),
                            result.getString("notaMinEvaluacion"),
                            result.getString("notaMediaEvaluacion"),
                            result.getString("porcientoAprobados"),
                            result.getString("porcientoSuspensos")
                    )
            );
        }
        db.close();
        return list;
    }

    /**
     * Añade una evaluación
     * @params evaluación a añadir
     */
    // El error lleva a la clase alumnosrepository
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
     * @param id
     * @return evaluación que ha sido eliminado o null si no lo encuentra
     * @throws SQLException
     */
    // El error lleva a la clase alumnosrepository
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
