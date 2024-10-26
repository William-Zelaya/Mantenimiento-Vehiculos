package modelosDAO;

import db.cn;
import modelos.Mantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MantenimientoDAO {

    Connection con;
    cn conexion;
    PreparedStatement ps;
    ResultSet rs;

    public MantenimientoDAO() throws ClassNotFoundException {
        conexion = new cn();
    }

// Método para listar mantenimientos
public List<Mantenimiento> listar() {
    List<Mantenimiento> lista = new ArrayList<>();
    String sql = "SELECT m.IDMantenimiento, m.FechaMantenimiento, v.MarcaVehiculo, v.ModeloVehiculo, m.Costo "
               + "FROM Mantenimiento m "
               + "JOIN Vehiculos v ON m.VehiculoID = v.IDVehiculo"; // Modificar la consulta para incluir marca y modelo

    try {
        con = conexion.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setIdMantenimiento(rs.getInt("IDMantenimiento"));
            mantenimiento.setFechaMantenimiento(rs.getString("FechaMantenimiento"));
            mantenimiento.setMarcaVehiculo(rs.getString("MarcaVehiculo")); // Agregar marca
            mantenimiento.setModeloVehiculo(rs.getString("ModeloVehiculo")); // Agregar modelo
            mantenimiento.setCosto(rs.getDouble("Costo"));
            lista.add(mantenimiento);
        }
    } catch (SQLException e) {
        System.err.println("Error al listar mantenimientos: " + e);
    }
    return lista;
}

    
    // Método para agregar un mantenimiento
    public boolean agregar(Mantenimiento mantenimiento) {
        String sql = "INSERT INTO Mantenimiento (FechaMantenimiento, VehiculoID, Costo) VALUES (?, ?, ?)";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, mantenimiento.getFechaMantenimiento());
            ps.setInt(2, mantenimiento.getVehiculoID());
            ps.setDouble(3, mantenimiento.getCosto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al agregar mantenimiento: " + e);
            return false;
        }
    }
}
