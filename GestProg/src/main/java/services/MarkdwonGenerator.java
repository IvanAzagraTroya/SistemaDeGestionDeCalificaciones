package services;

import fun.mingshan.markdown4j.type.block.CodeBlock;
import fun.mingshan.markdown4j.type.block.TableBlock;
import models.Alumno;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Iván Azagra
 */
public class MarkdwonGenerator implements IStorageAlumno {
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "data";
    private final String alumnoMarkdown = dir +File.separator + "GestorDeEvaluaciones.md";

    public MarkdwonGenerator() {
        init()
    }

    private void init(){
        Path path = Paths.get(dir);
        if(!Files.exists(path)) {
            try{
                Files.createDirectories(path);
            }catch(IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean save(List<Optional<Alumno>>){
        CodeBlock codeBlock = CodeBlock.builder().
                                language(CodeBlock.Language.JAVA.getDesc()).
                                content("# Gestión de calificaciones").
                                build();
        List<TableBlock.TableRow> rows = new ArrayList<>();
    }
}
