package models;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Iván Azagra Troya
 */
@Builder
public class Backup {
    private final String createdAt = LocalDateTime.now().toString();
    private List<Alumno> alumnos;
    private List<Prueba> pruebas;
    private List<Evaluacion> evaluaciones;

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public List<Prueba> getPruebas() {
        return pruebas;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
}
