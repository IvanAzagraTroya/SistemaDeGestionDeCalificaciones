package repositories;

import models.Evaluacion;

import java.sql.SQLException;
import java.util.Optional;

public class EvaluacionRepository implements IEvaluacionRepository {
    private static EvaluacionRepository instance;

    private EvaluacionRepository() {

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
     *
     */
    public Optional<Evaluacion> findByNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM evaluacion WHERE nombre = ?";

    }
}
