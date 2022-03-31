package controllers;

import models.Alumno;
import models.Backup;
import models.Evaluacion;
import repositories.IAlumnoRepository;
import repositories.IEvaluacionRepository;
import repositories.IPruebasRepository;
import services.IStorageBackup;

import java.sql.SQLException;

/**
 * @author Iván Azagra Troya
 */
public class BackupManager {
    private final IAlumnoRepository alumnoRepository;
    private final IPruebasRepository pruebasRepository;
    private final IEvaluacionRepository evaluacionRepository;
    private final IStorageBackup storageBackup;

    public BackupManager(IAlumnoRepository alumnoRepository, IPruebasRepository pruebasRepository,
                         IEvaluacionRepository evaluacionRepository, IStorageBackup storageBackup) {
        this.alumnoRepository = alumnoRepository;
        this.pruebasRepository = pruebasRepository;
        this.evaluacionRepository = evaluacionRepository;
        this.storageBackup = storageBackup;
    }

    /**
     * Importa los datos desde un fichero de backup
     */
    public void importarDatos() throws SQLException {
        System.out.println("Importando datos de Backup: "+ storageBackup.getStoragePath());
        var backup = storageBackup.load();
        System.out.println("Importando los datos de los alumnos");
        if(backup.getAlumnos().size() > 0) {
            alumnoRepository.clearAll();
            for(Alumno alumno : backup.getAlumnos()) {
                alumnoRepository.save(alumno);
            }
            System.out.println("Alumnos importados: "+backup.getAlumnos().size() +" alumnos");
        } else {
            System.out.println("Ha ocurrido un error durante la importación de alumnos");
        }
        System.out.println("Importando evaluaciones");
        if(backup.getEvaluaciones().size() > 0) {
            evaluacionRepository.clearAll();
            for(Evaluacion evaluacion : backup.getEvaluaciones()) {
                evaluacionRepository.save(evaluacion);
            }
            System.out.println("Evaluaciones importadas: " +backup.getEvaluaciones().size() +" evaluaciones");
        }else {
            System.out.println("Ha ocurrido un error al importar las evaluaciones");
        }
    }

    /**
     * Exporta los datos desde un fichero de Backup
     */
    // fallo: Non-static method 'builder()' cannot be referenced from a static context
    public void exportarDatos() throws SQLException {
        System.out.println("Exportando datos a fichero de backup");
        var alumnos = alumnoRepository.findAll();
        var evaluaciones = evaluacionRepository.findAll();
        var prueba = pruebasRepository.findAll();
        Backup backup = Backup.builder()
                .alumnos(alumnos)
                .evaluaciones(evaluaciones)
                .prueba(prueba)
                .build();
        var res = storageBackup.save(backup);
        if(res) {
            System.out.println("Exportando " + backup.getAlumnos().size() + " alumnos");
            System.out.println("Exportando " + backup.getEvaluaciones().size() + " evaluaciones");
            System.out.println("Exportando " + backup.getPruebas().size() +" pruebas");
            System.out.println("Datos exportados con éxito en: " + storageBackup.getStoragePath());
        } else {
            System.out.println("Error al exportar los datos");
        }
    }
}
