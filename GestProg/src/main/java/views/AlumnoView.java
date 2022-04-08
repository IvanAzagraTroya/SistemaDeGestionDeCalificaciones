package views;

import controllers.DataBaseManager;
import models.Alumno;
import repositories.AlumnoRepository;
import services.MarkdwonGenerator;
import utils.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlumnoView {
    private static AlumnoView instance;
    private final AlumnoRepository repo = AlumnoRepository.getInstance(DataBaseManager.getInstance());


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
        List<Alumno> alumnos = null;
        try {
            alumnos = repo.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //alumnos.sort(new AlumnoComparatorAlfabeto());
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
            var existe = repo.findByNombre(nombre);

            // Tomamos los nuevos datos o nos quedamos con los antiguos si son blancos.
            String nuevoNombre = Console.getString("Nuevo nombre del alumno (anterior: " + existe.get().getNombre() + "): ");
            nuevoNombre = (nuevoNombre.isEmpty()) ? existe.get().getNombre() : nuevoNombre;

            String nuevosApellidos = Console.getString("Nuevos apellidos del alumno (anterior: " + existe.get().getApellidos() + "): ");
            nuevosApellidos = (nuevosApellidos.isEmpty()) ? existe.get().getApellidos() : nuevosApellidos;

            // Es importante crear un objeto nuevo, porque si no al ser referencias tocamos el del repositorio
            // Esto no pasará con ficheros o bases de datos
            var update = existe.get().clone()
                    .nombre(nuevoNombre).
                    get().apellidos(nuevosApellidos)
                    ;

            // Actualizamos el alumno
            var res = repo.update(existe.get().getId(), update.get());
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
        int posicion = Console.getInt("POsicion en la lista del alumno: ");
        try {
            var res = repo.delete(posicion);
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

        String hasContinua = Console.getString("¿Tiene evaluación continua?");


        var alumno = new Alumno().builder().
                nombre(nombre).
                apellidos(apellidos).
                dni(dni).
                telefono(telefono).
                evaluacionContinua(hasContinua).
                build();
        Optional<Alumno> res = repo.save(alumno);
        if(res.isPresent()){
            System.out.println("alumno guardado correctamente");
        }else{
            System.err.println("no se ha podido guardar el alumno");
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
            System.out.println("6. Importar/Exportar");
            System.out.println("0. Salir");
            System.out.println();
            String opcion = Console.getString("Elige una opción [0-7]: ");
            // Expresión regular para validar la opción
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
                        exportar();
                        break;
                    case "0":
                        salir();
                        break;
                }
            }
        } while (true);
    }

    private void exportar(){
       var mdGenerator = new MarkdwonGenerator();
        List<Alumno> lista = null;
        try {
            lista = repo.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("creando el archivo");
        mdGenerator.generarMD(lista);
    }

    /**
     * Incluye los alumnos en una lista
     */
    private List<Optional<Alumno>> inputLineasAlumnos() {
        var lineas = new ArrayList<Optional<Alumno>>();
        var correcto = false;
        do {
            var error = false;
            var alumnos = Console.getString("Nombre de los alumnos separados por coma: ").split(",");
            // revisamos que las matriculas introducidas existan
            for (String nombre : alumnos) {
                try {
                    var alumno = repo.findByNombre(nombre.trim());
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
            var res = repo.findByNombre(nombre);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
    }
}