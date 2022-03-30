package comparators;

import models.Alumno;

import java.util.Comparator;

public class AlumnoComparatorAlfabeto implements Comparator<Alumno> {
    @Override
    public int compare(Alumno a1, Alumno a2) {
        return a1.getNombre().compareTo(a2.getNombre());
    }
}
