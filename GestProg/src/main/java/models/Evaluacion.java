package models;

/**
 * @author Iván Azagra
 */
public class Evaluacion {

    private int idEvaluacion;
    private String nombreEvaluacion;
    private float notaMaxEvaluacion;
    private float notaMinEvaluacion;
    private float notaMediaEvaluacion;
    private int porcientoAprobados;
    private int porcientoSuspensos;


    public Evaluacion(int idEvaluacion, String nombreEvaluacion, float notaMaxEvaluacion,
                      float notaMinEvaluacion, float notaMediaEvaluacion, int porcientoAprobados, int porcientoSuspensos){
        this.idEvaluacion = idEvaluacion;
        this.nombreEvaluacion = nombreEvaluacion;
        this.notaMaxEvaluacion = notaMaxEvaluacion;
        this.notaMinEvaluacion = notaMinEvaluacion;
        this.notaMediaEvaluacion = notaMediaEvaluacion;
        this.porcientoAprobados = porcientoAprobados;
        this.porcientoSuspensos = porcientoSuspensos;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public float getNotaMaxEvaluacion() {
        return notaMaxEvaluacion;
    }

    public void setNotaMaxEvaluacion(float notaMaxEvaluacion) {
        this.notaMaxEvaluacion = notaMaxEvaluacion;
    }

    public float getNotaMinEvaluacion() {
        return notaMinEvaluacion;
    }

    public void setNotaMinEvaluacion(float notaMinEvaluacion) {
        this.notaMinEvaluacion = notaMinEvaluacion;
    }

    public float getNotaMediaEvaluacion() {
        return notaMediaEvaluacion;
    }

    public void setNotaMediaEvaluacion(float notaMediaEvaluacion) {
        this.notaMediaEvaluacion = notaMediaEvaluacion;
    }

    public int getPorcientoAprobados() {
        return porcientoAprobados;
    }

    public void setPorcientoAprobados(int porcientoAprobados) {
        this.porcientoAprobados = porcientoAprobados;
    }

    public int getPorcientoSuspensos() {
        return porcientoSuspensos;
    }

    public void setPorcientoSuspensos(int porcientoSuspensos) {
        this.porcientoSuspensos = porcientoSuspensos;
    }

}
