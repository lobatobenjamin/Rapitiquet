package run;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.ini4j.Ini;

public class Conexion {

    static final String filename = "Parametros.ini";
    public static Ini ini = null;
    private String host;
    private String bd;
    private String user;
    private String pass;

    public Conexion() {
        cargarIni();
    }

    public Conexion(String host, String bd, String user, String pass) {
        this.host = host;
        this.bd = bd;
        this.user = user;
        this.pass = pass;
    }
    private Connection conn = null;

    public Connection getConn() {
        return conn;
    }

    public void conectarSQL() throws InstantiationException, IllegalAccessException, SQLException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Imposible cargar el driver JDBC - SQL Server...");
        }

        try {
            this.conn = (Connection) DriverManager.getConnection("jdbc:sqlserver://" + host + ";databaseName=" + bd + ";user = " + user + ";password = " + pass);
            System.out.println("jdbc:sqlserver://" + host + ";databaseName=" + bd);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo establecer una conexion con el servidor...");
        }
    }

    public Conexion conectarICG() {
        Conexion conICG = new Conexion(ini.get("CONEXION_ICG").get("HOST"), ini.get("CONEXION_ICG").get("BD"), ini.get("CONEXION_ICG").get("USUARIO"), ini.get("CONEXION_ICG").get("CLAVE"));
        return conICG;
    }

    public void cerrarConeccion() throws SQLException {
        this.conn.close();
    }

    public static void cargarIni() {
        try {
            ini = new Ini(new FileReader(filename));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra o está dañado el archivo de Conexiones...");
            ex.printStackTrace();
        }
    }
}
