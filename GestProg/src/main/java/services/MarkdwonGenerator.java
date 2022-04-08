package services;

import models.Alumno;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Iván Azagra
 */
public class MarkdwonGenerator{
    private final String path = System.getProperty("User.dir")+ File.separator+"GestProg"+File.separator+"src"+File.separator+"main"+File.separator+"resources";
    private final LocalDateTime date = LocalDateTime.now();
    public void generarMD(List<Alumno> lista){

        File archivo = new File(path+File.separator+"Alumnado.md");

        try {
            FileWriter fw = new FileWriter(archivo);
            fw.write("# GESTIÓN DE ALUMNOS\n");
            lista.forEach(v->{
                try {
                    fw.write(generadorTexto(v));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            fw.write("Creado el dia "+date+"\n");
            fw.write("Generado en: "+"ms");
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generadorTexto(Alumno a){

        String texto = "## Alumno "+"_"+a.getNombre()+"_"+" "+a.getApellidos()+"_"+"\n"
                +" * DNI -> "+"_"+a.getDni()+"_"+"\n"
                +" * Evaluacion continua -> "+"_"+a.getEvaluacionContinua()+"_"+"\n"
                +" * Telefono de contacto -> "+"_"+a.getTelefono()+"_"+"\n";
        return texto;
    }
}
