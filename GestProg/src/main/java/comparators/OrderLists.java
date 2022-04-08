package comparators;

import models.Alumno;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderLists {

    /**
     * ordena la lista alfabeticamente
     * @param alumnos la lista de alumnos a ordenar
     * @return la lista de alumnos ordenada
     */
    public List<Alumno> orderByName(List<Alumno> alumnos){
        return alumnos.stream().sorted(Comparator.comparing(Alumno::getNombre)).collect(Collectors.toList());
    }

    /**
     * ordena la lista de alumnos por su poicion en la lista, que, en este caso, es la id
     * @param alumnos la lista de alumnos a ordenar
     * @return la lista de alumnos ordenada
     */
    public List<Alumno> orderByListPosition(List<Alumno> alumnos){
        return alumnos.stream().sorted(Comparator.comparing(Alumno::getId)).collect(Collectors.toList());
    }
}
