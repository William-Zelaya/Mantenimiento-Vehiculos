package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cn {

    private Connection con;

    public cn() throws ClassNotFoundException {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión con la base de datos 'mantenimientovehiculos'
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mantenimientovehiculos", "root", "");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return con;
    }
}

