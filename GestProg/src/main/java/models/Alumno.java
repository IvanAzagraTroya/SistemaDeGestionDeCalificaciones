package models;

import java.util.Objects;

/**
 * Clase que representa a un alumno
 */
public class Alumno {
    private static int contador;
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private Boolean evaluacionContinua;

    public Alumno(){
        this.id = ++contador;
    }

    public Alumno(String nombre, String apellidos, String dni,
                  String telefono, Boolean evaluacionContinua) {
        this.id = ++contador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.evaluacionContinua = evaluacionContinua;
    }

    //Para el clone

    public Alumno(int id, String nombre, String apellidos, String dni,
                  String telefono, Boolean evaluacionContinua) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.evaluacionContinua = evaluacionContinua;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getEvaluacionContinua() {
        return evaluacionContinua;
    }

    public void setEvaluacionContinua(Boolean evaluacionContinua) {
        this.evaluacionContinua = evaluacionContinua;
    }

    public Alumno nombre(String nombre) {
        this.nombre=nombre;
        return this;
    }

    public Alumno apellidos(String apellidos) {
        this.apellidos=apellidos;
        return this;
    }

    public Alumno dni(String dni) {
        this.dni=dni;
        return this;
    }

    public Alumno telefono(String telefono) {
        this.telefono=telefono;
        return this;
    }

    public Alumno evaluacionContinua(Boolean evaluacionContinua) {
        this.evaluacionContinua=evaluacionContinua;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;
        Alumno alumno = (Alumno) o;
        return getId() == alumno.getId() && Objects.equals(getNombre(), alumno.getNombre()) && Objects.equals(getApellidos(), alumno.getApellidos()) && Objects.equals(getDni(), alumno.getDni()) && Objects.equals(getTelefono(), alumno.getTelefono()) && Objects.equals(getEvaluacionContinua(), alumno.getEvaluacionContinua());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellidos(), getDni(), getTelefono(), getEvaluacionContinua());
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", evaluacionContinua=" + evaluacionContinua +
                '}';
    }

    @Override
    public Alumno clone() {
        return new Alumno(this.id, this.nombre, this.apellidos, this.dni,
                this.telefono, this.evaluacionContinua);
    }

    public String toString(String separator) {
        return id + separator + nombre + separator + apellidos + separator + dni +
                separator + telefono + separator + evaluacionContinua;
    }
}
