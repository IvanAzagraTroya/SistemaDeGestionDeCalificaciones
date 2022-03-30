package views;

import comparators.AlumnoComparatorAlfabeto;
import controllers.AlumnoController;
import controllers.EvaluacionController;
import models.Alumno;
import repositories.AlumnoRepository;
import repositories.EvaluacionRepository;
import services.StorageAlumnosCSVFile;
import utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlumnoView {
    private static AlumnoView instance;
    // Le inyectamos la dependencia.
    // private final AlumnoController alumnoController = AlumnoController.getInstance(new AlumnoRepository());
    private final AlumnoController alumnoController = new AlumnoController(new AlumnoRepository(), new StorageAlumnosCSVFile());
    // No necesitamos hacer un singleton
    private final EvaluacionController evaluacionController = new EvaluacionController(new EvaluacionRepository());

    private AlumnoView() {
    }

    public static AlumnoView getInstance() {
        if (instance == null) {
            instance = new AlumnoView();
        }
        return instance;
    }

    /**
     * Salir del programa
     */
    private static void salir() {
        System.out.println("¡Hasta pronto!");
        System.exit(0);
    }

    /**
     * Muestra los alumnos ordenados por nombre
     */
    private void mostrarAlumnos() {
        System.out.println("Mostrar alumnos");
        // Obtengo los países
        List<Alumno> alumnos = alumnoController.getAllAlumnos();
        alumnos.sort(new AlumnoComparatorAlfabeto());
        System.out.println("Hay " + alumnos.size() + " alumnos");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    /**
     * Actualiza un alumno
     */
    private void actualizarAlumno() {
        System.out.println("Actualizar alumno");
        String nombre = Console.getString("Nombre del alumno: ");
        System.out.println("Introduce los nuevos datos o deje en blanco para mantener los actuales");

        try {
            // Comprobamos si existe el alumno antes de pedirle los datos
            var existe = alumnoController.getAlumnoByNombre(nombre);

            // Tomamos los nuevos datos o nos quedamos con los antiguos si son blancos.
            String nuevoNombre = Console.getString("Nuevo nombre del alumno (anterior: " + existe.getNombre() + "): ");
            nuevoNombre = (nuevoNombre.isEmpty()) ? existe.getNombre() : nuevoNombre;

            String nuevosApellidos = Console.getString("Nuevos apellidos del alumno (anterior: " + existe.getApellidos() + "): ");
            nuevosApellidos = (nuevosApellidos.isEmpty()) ? existe.getApellidos() : nuevosApellidos;

            // Es importante crear un objeto nuevo, porque si no al ser referencias tocamos el del repositorio
            // Esto no pasará con ficheros o bases de datos
            var update = existe.clone()
                    .nombre(nuevoNombre)
                    .apellidos(nuevosApellidos)
                    ;

            // Actualizamos el alumno
            var res = alumnoController.updateAlumno(existe.getId(), update);
            System.out.println("Alumno actualizado");
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Error al actualizar un alumno. " + e.getMessage());
        }
    }

    /**
     * Elimina un alumno
     */
    private void eliminarAlumno() {
        System.out.println("Eliminar alumno");
        String nombre = Console.getString("Nombre del alumno: ");
        try {
            var res = alumnoController.deleteAlumno(nombre);
            System.out.println("Alumno eliminado correctamente");
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    /**
     * Crea un alumno
     */
    private void crearAlumno() {
        System.out.println("Crear alumno");
        System.out.println("Los datos no pueden estar vacíos");
        String nombre = Console.getString("Nombre del alumno: ");
        String apellidos = Console.getString("Apellidos del alumno: ");
        String dni = Console.getString("DNI del alumno: ");
        String telefono = Console.getString("Teléfono del alumno: ");

        Boolean hasContinua = Console.getBoolean("¿Tiene evaluación continua?");


        Alumno alumno = new Alumno().
                nombre(nombre).apellidos(apellidos).dni(dni).telefono(telefono).evaluacionContinua(hasContinua);


        // insertamos el alumno
        try {
            var res = alumnoController.crearAlumno(alumno);
            System.out.println("Alumno creado correctamente");
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Error al crear: " + e.getMessage());
        }
    }

    public void menu() {
        System.out.println("Gestión de Alumnos");
        System.out.println("=================");
        // Bucle infinito a la espera de una opción o salir
        do {
            System.out.println();
            System.out.println("1. Crear alumno");
            System.out.println("2. Eliminar alumno");
            System.out.println("3. Actualizar alumno");
            System.out.println("4. Mostrar lista de alumnos");
            System.out.println("5. Consultar alumno");
            System.out.println("6. Crear evaluaciones");
            System.out.println("7. Importar/Exportar");
            System.out.println("0. Salir");
            System.out.println();
            String opcion = Console.getString("Elige una opción [0-7]: ");
            // Expresion regular para validar la opción
            var regex = "[0-8]";
            if (opcion.matches(regex)) {
                switch (opcion) {
                    case "1":
                        crearAlumno();
                        break;
                    case "2":
                        eliminarAlumno();
                        break;
                    case "3":
                        actualizarAlumno();
                        break;
                    case "4":
                        mostrarAlumnos();
                        break;
                    case "5":
                        getAlumnoByNombre();
                        break;
                    case "6":
                        //crearEvaluaciones();
                        break;
                    case "7":
                        importarExportar();
                        break;
                    case "0":
                        salir();
                        break;
                }
            }
        } while (true);
    }

    private void importarExportar() {
        System.out.println("Copia de Seguridad de Datos");
        var regex = "importar|exportar";
        var opcion = "";
        do {
            opcion = Console.getString("Importar/Exportar datos: ").toLowerCase(Locale.getDefault());
            if (!opcion.matches(regex)) {
                System.out.println("Opción incorrecta");
            }
        } while (!opcion.matches(regex));

        switch (opcion) {
            case "importar":
                alumnoController.importarDatos();
                break;
            case "exportar":
                alumnoController.exportarDatos();
                break;
        }

    }

    /**
     * Crear evaluaciones
     */
    /*private void crearEvaluaciones() {
        System.out.println("Realizar Evaluaciones");
        var alumnos = inputLineasAlumnos();
        try {
            var res = evaluacionController.crearEvaluaciones("Acuerdo Internacional", "Un acuerdo", alumnos);
            System.out.println("El acuerdo se firmó correctamente");
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Error al crear las evaluaciones: " + e.getMessage());
        }
    }*/

    /**
     * Incluye los alumnos en una lista
     */
    private List<Alumno> inputLineasAlumnos() {
        var lineas = new ArrayList<Alumno>();
        var correcto = false;
        do {
            var error = false;
            var alumnos = Console.getString("Nombre de los alumnos separados por coma: ").split(",");
            // revisamos que las matriculas introducidas existan
            for (String nombre : alumnos) {
                try {
                    var alumno = alumnoController.getAlumnoByNombre(nombre.trim());
                    lineas.add(alumno);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    error = true;
                }
            }

            if (lineas.isEmpty() || error) {
                System.out.println("No se ha introducido un alumno válido. Vuelva a intentarlo.");
            } else {
                correcto = true;
            }
        } while (!correcto);
        return lineas;
    }

    /**
     * Obtiene un alumno por su nombre
     */
    private void getAlumnoByNombre() {
        System.out.println("Mostrar alumno");
        String nombre = Console.getString("Nombre del alumno: ");
        try {
            var res = alumnoController.getAlumnoByNombre(nombre);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
    }
}