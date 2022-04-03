package models;

public class Evaluacion {

    private int idEvaluacion;
    private String nombreEvaluacion;
    //Podría ponerse como float para aceptar decimales ya que para la evaluación final
    // me parece que si se toman en cuenta en la evaluación final aunque no se muestre en el boletín
    private String notaMaxEvaluacion;
    private String notaMinEvaluacion;
    private String notaMediaEvaluacion;
    private String porcientoAprobados;
    private String porcientoSuspensos;


    public Evaluacion(int idEvaluacion, String nombreEvaluacion, String notaMaxEvaluacion,
                      String notaMinEvaluacion, String notaMediaEvaluacion, String porcientoAprobados, String porcientoSuspensos){
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

    public String getNotaMaxEvaluacion() {
        return notaMaxEvaluacion;
    }

    public void setNotaMaxEvaluacion(String notaMaxEvaluacion) {
        this.notaMaxEvaluacion = notaMaxEvaluacion;
    }

    public String getNotaMinEvaluacion() {
        return notaMinEvaluacion;
    }

    public void setNotaMinEvaluacion(String notaMinEvaluacion) {
        this.notaMinEvaluacion = notaMinEvaluacion;
    }

    public String getNotaMediaEvaluacion() {
        return notaMediaEvaluacion;
    }

    public void setNotaMediaEvaluacion(String notaMediaEvaluacion) {
        this.notaMediaEvaluacion = notaMediaEvaluacion;
    }

    public String getPorcientoAprobados() {
        return porcientoAprobados;
    }

    public void setPorcientoAprobados(String porcientoAprobados) {
        this.porcientoAprobados = porcientoAprobados;
    }

    public String getPorcientoSuspensos() {
        return porcientoSuspensos;
    }

    public void setPorcientoSuspensos(String porcientoSuspensos) {
        this.porcientoSuspensos = porcientoSuspensos;
    }

}
