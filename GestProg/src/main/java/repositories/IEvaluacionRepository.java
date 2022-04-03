package repositories;

import models.Evaluacion;

import java.sql.SQLException;

public interface IEvaluacionRepository extends CRUDRepository<Evaluacion, Integer>{

    void clearAll()  throws SQLException;
}
