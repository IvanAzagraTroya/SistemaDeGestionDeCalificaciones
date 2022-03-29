package models;

import java.util.Objects;

public class Prueba {
    private static int contador = 0;
    private int id;
    private String nombre;
    //private dateAt date;
    private int calificacion;

    public Prueba(){
        this.id = ++contador;
    }

    // Falta por añadir el atributo de la fecha de realización
    public Prueba(String nombre, /*dateAt,*/ int calificacion) {
        this.id = ++contador;
        this.nombre = nombre;
        this.calificacion = calificacion;
    }

    //Para el clone
    private Prueba(int id, String nombre, /*dateAt,*/ int calificacion){
        this.id = id;
        this.nombre = nombre;
        this.calificacion = calificacion;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Prueba.contador = contador;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Prueba{" +"id= " +id+
                "nombre= " +nombre+
                "dateAt= "+
                "calificacion= " +calificacion +"}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, calificacion); //falta la fecha
    }

    @Override
    public Prueba clone() {
        return new Prueba(this.id, this.nombre, this.calificacion); //falta la fecha
    }
}
