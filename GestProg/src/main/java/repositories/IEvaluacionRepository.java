package repositories;

import models.Evaluacion;

public interface IEvaluacionRepository extends CRUDRepository<Evaluacion, Integer>{

    Evaluacion findByNota(int nota);

    Evaluacion update(int idEvaluacion, Evaluacion evaluacion, boolean mod);

    void clearAll();
}
