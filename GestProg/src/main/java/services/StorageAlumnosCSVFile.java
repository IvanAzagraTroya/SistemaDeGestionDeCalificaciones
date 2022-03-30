package services;
import models.Alumno;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StorageAlumnosCSVFile implements IStorageAlumno{
    private final Path currentRelativePath = Paths.get("");
    private String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "data";
    private final String alumnosFile = dir + File.separator + "alumnos.txt";
    private final String fieldSeparator = ";";


    public StorageAlumnosCSVFile() {
        init();
    }

    private void init() {
        Path path = Paths.get(dir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean save(List<Alumno> lista) {
        PrintWriter f = null;
        boolean result = false;
        try {
            f = new PrintWriter(new FileWriter(alumnosFile));

            for (var alumno : lista) {
                f.println(alumno.toString(fieldSeparator));
            }

            result = true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (f != null) {
                f.close();
            }
        }
        return result;
    }

    @Override
    public List<Alumno> load() {
        File fichero;
        BufferedReader f = null;
        List<Alumno> lista = new ArrayList<>();
        try {
            fichero = new File(alumnosFile);
            f = new BufferedReader(new FileReader(fichero));

            String linea;
            while ((linea = f.readLine()) != null) {
                lista.add(getAlumno(linea));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return lista;
    }

    private Alumno getAlumno(String linea) {
        String[] campos = linea.split(fieldSeparator);
        return new Alumno(
                Integer.parseInt(campos[0]),
                campos[1],
                campos[2],
                campos[3],
                campos[4],
                Boolean.getBoolean(campos[5])

        );
    }

    @Override
    public String getStoragePath() {
        return alumnosFile;
    }
}