package services;

import models.Alumno;
import models.Evaluacion;

public class MarkdwonGenerator {
    public String mdGenerator() {
        String header = "# Gestión de calificaciones";
        String separator = "----------- ";
        String data = " Datos de la evaluación";
        String alumnos;
        for (int i = 0; i < Alumnos size; i++){ // dejo puesto para completar más tarde
            alumnos = "*" + Alumno.getNombre();
        }
        String generatedAt = "**Generado el día**: " +;
        String time = "**Proceso terminado en**: " +;
        String plantilla = header + "\n" + separator + "\n" + data + "\n" + alumnos + "\n" + separator + "\n" + generatedAt + "\n" + time;
        return plantilla;
    }
}
