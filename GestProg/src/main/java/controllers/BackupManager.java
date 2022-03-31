package controllers;

import repositories.IAlumnoRepository;
import repositories.IEvaluacionRepository;
import repositories.IPruebasRepository;

public class BackupManager {
    private final IAlumnoRepository alumnoRepository;
    private final IPruebasRepository pruebasRepository;
    private final IEvaluacionRepository evaluacionRepository;
}
