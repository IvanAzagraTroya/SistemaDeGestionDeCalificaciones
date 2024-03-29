package utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *Clase util para la obtención de datos de propiedades de la aplicación
 * @author Iván Azagra Troya
 * @version 1.0
 */
public class ApplicationProperties {
    private final Properties properties;

    public ApplicationProperties(){
        properties = new Properties();
        try {
            // Lee el fichero de propiedades en la carpeta resources
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        }catch (IOException ex) {
            System.err.println("IOException Ocurrido al leer el fichero de propiedades: " +ex.getMessage());
            Logger.getLogger(getClass().getName()).log(Level.ALL, "IOException Ocurrido al leer el fichero" +
                    "de propiedades: " +ex.getMessage());
        }
    }

    public String readProperty(String keyName) {
        return properties.getProperty(keyName, "No existe esa clave en el fichero de propiedades");
    }
}
