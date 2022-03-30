package models;

public class Evaluacion {

    private int idEvaluacion;
    private String nombreEvaluacion;
    //Podría ponerse como float para aceptar decimales ya que para la evaluación final
    // me parece que si se toman en cuenta en la evaluación final aunque no se muestre en el boletín
    private int notaMaxEvaluacion;
    private int notaMinEvaluacion;
    private int notaMediaEvaluacion;
    private int porcientoAprobados;
    private int porcientoSuspensos;


    public Evaluacion(int idEvaluacion, String nombreEvaluacion, int notaMaxEvaluacion,
                      int notaMinEvaluacion, int notaMediaEvaluacion, int porcientoAprobados, int porcientoSuspensos){
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

    public int getNotaMaxEvaluacion() {
        return notaMaxEvaluacion;
    }

    public void setNotaMaxEvaluacion(int notaMaxEvaluacion) {
        this.notaMaxEvaluacion = notaMaxEvaluacion;
    }

    public int getNotaMinEvaluacion() {
        return notaMinEvaluacion;
    }

    public void setNotaMinEvaluacion(int notaMinEvaluacion) {
        this.notaMinEvaluacion = notaMinEvaluacion;
    }

    public int getNotaMediaEvaluacion() {
        return notaMediaEvaluacion;
    }

    public void setNotaMediaEvaluacion(int notaMediaEvaluacion) {
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
