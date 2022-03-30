package controllers;

import utils.ApplicationProperties;
//import org.apache.ibatis.jdbc.ScriptRunner;
//import lombok.NonNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.sql.*;
import java.util.Optional;

//Error en el @NonNull de lombok, no me encuentra la dependencia

/**
 * Manejador de Bases de Datos Relacionales
 *
 * @author Iván Azagra Troya
 * @version 1.0
 */
public class DataBaseManager {
    private static DataBaseManager controller;
    private boolean fromPropierties = true;
    private String serverUrl;
    private String serverPort;
    private String dataBaseName;
    private String user;
    private String password;

    private String jdbcDriver;
    // Maneja las conexiones y respuestas de las mismas
    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * Constructor privado para singleton, inicializa la configuración de acceso al servidor
     * y abre la conexión
     * @return instancia única de la clase DataBaseManager
     */
    private DataBaseManager() {
        if(fromPropierties) {
            initConfigFromProperties();
        }/*else {
            initConfig();
        }*/
    }

    /**
     * Devuelve instancia del controlador
     * @return instancia de la clase
     */
    public static DataBaseManager getInstance() {
        if(controller == null) {
            controller = new DataBaseManager();
        }
        return controller;
    }

    /*
    /**
     * Carga la configuración de acceso al servidor de la base de datos
     * Esta clase es la configuración hardcodeada
     /

    // Esta clase deberá ser modificada para hacerla accesible dinámicamente a través de ficheros .env o properties
    private void initConfig() {
        serverUrl = "localhost";
        serverPort = "3306";
        dataBaseName = "dam";
        jdbcDriver = "com.mysql.cj.jdbc.Driver";
        user = "dam";
        password = "dam1234";
    }
     */

    /**
     * Carga la configuración de acceso al servidor de Base de Datos desde properties
     */
    private void initConfigFromProperties() {
        ApplicationProperties properties = new ApplicationProperties();
        serverUrl = properties.readProperty("database.server.url");
        serverPort = properties.readProperty("database.server.port");
        dataBaseName = properties.readProperty("database.name");
        jdbcDriver = properties.readProperty("database.jdbc.driver");
        user = properties.readProperty("database.user");
        password = properties.readProperty("database.password");
    }

    /**
     * Abre la conexión con el servidor de base de datos
     * @throws SQLException Servidor no accesible por problemas de conexión de datos de acceso incorrectos
     */
    // Dejo todas las conexiones para solo tener que descomentar la que se vaya a utilizar
    public void open() throws SQLException {
        //String url = "jdbc:sqlite:"+this.ruta+this.bbdd;
        // MySQL jdbc:mysql://localhost/prueba", "root", "1dam"
        String url = "jdbc:mariadb://" + this.serverUrl + ":" + this.serverPort + "/" + this.dataBaseName;
        // System.out.println(url);
        // Obtenemos la conexión
        connection = DriverManager.getConnection(url, user, password);
    }

    /**
     * Cierra la conexión con el servidor de la base de datos
     * @throws SQLException Servidor no responde o no puede realizar el cierre
     */
    public void close() throws SQLException {
        if(preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }

    /**
     * Realiza una consulta a la base de datos de manera "preparada" obteniendo parámetros opcionales en caso necesario
     * @param querySQL consulta SQL de tipo select
     * @param params parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    private ResultSet executeQuery(@NonNull String querySQL, Object... params) throws SQLException {
        preparedStatement = connection.prepareStatement(querySQL);
        // Se pasan los parámetros usando preparedStatement
        for(int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

    /**
     * Realiza una consulta select a la base de datos de manera "preparada" obteniendo los parámetros opcionales si son necesarios
     * @param querySQL consulta SQL de tipo select
     * @param params parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    public Optional<ResultSet> select(@NonNull String querySQL, Object... params) throws SQLException {
        return Optional.of(executeQuery(querySQL, params));
    }

    /**
     * Realiza una consulta de tipo insert a la base de datos de manera "preparada" obteniendo los parametros opcionales si fuesen necesarios
     *@param insertSQL consulta SQL de tipo insert
     * @param params    parámetros de la consulta parametrizada
     * @return Clave del registro insertado
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public Optional<ResultSet> insert(@NonNull String insertSQL, Object... params) throws SQLException {
        // Cpm return generated keys obtenemos las claves generadas las claves es autonumérico, si no es autonumérico quitar
        preparedStatement = connection.prepareStatement(insertSQL, preparedStatement.RETURN_GENERATED_KEYS);
        // Vamos a pasarle los parametros usando preparedStatement
        for(int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        preparedStatement.executeUpdate();
        return Optional.of(preparedStatement.getGeneratedKeys());
    }

    /**
     * Realiza una consulta de tipo update de manera "preparada" con los parametros opcionales si son necesarios
     * @param updateSQL consulta SQL de tipo update
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros actualizados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public int update(@NonNull String updateSQL, Object... params) throws SQLException {
        return updateQuery(updateSQL, params);
    }

    /**
     * Realiza una consulta de tipo delete de manera "preparada" con los parametros opcionales si son encesarios
     * @param deleteSQL consulta SQL de tipo delete
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public int delete(@NonNull String deleteSQL, Object... params) throws SQLException {
        return updateQuery(deleteSQL, params);
    }

    /**
     * Realiza una consulta de tipo update, es decir que modifca los datos, de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param genericSQL consulta SQL de tipo update, delete, creted, etc.. que modifica los datos
     * @param params     parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */

    private int updateQuery(@NonNull String genericSQL, Object... params) throws SQLException {
        // Con return generated keys obtenemos las claves generadas
        preparedStatement = connection.prepareStatement(genericSQL);
        // Le pasamos los parametros usando preparedStatement
        for(int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeUpdate();
    }

    /**
     * Crea una consulta genérica para crear tablas, vistas, procedimientos
     *
     * @param genericSQL consulta genérica
     * @return si ha tenido, 1
     * @throws SQLException no se ha podido realizar la operación
     */
    public int genericUpdate(@NonNull String genericSQL) throws SQLException {
        // Con return generated keys obtenemos las claves generadas
        preparedStatement = connection.prepareStatement(genericSQL);
        return preparedStatement.executeUpdate();
    }
    // Comprobar los fallos de ScriptRunner por el import de apache
    /**
     * Carga los datos desde un fichero externo
     *
     * @param sqlFile
     * @throws FileNotFoundException
     */
    public void initData(@NonNull String sqlFile, boolean logWriter) throws FileNotFoundException {
        // Usa el import de apache jdbc scriptRunner
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FIleReader(sqlFile));
        sr.setLogWriter(null);
        sr.runScript(reader);
    }

    // No sé si los necesitamos pero los dejo por si nos hiciesen falta a la hora de usar la base de datos
    /**
     * Inicia una transacción
     *
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    /**
     * Confirma una transacción
     * @throws SQLException
     */
    public void commit() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    /**
     * Cancela una transacción
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
