package modelosDAO;

import db.cn;  // Asegúrate de que esta clase exista
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelos.Vehiculo;

public class VehiculoDAO {

    private cn CN;  // Conexión usando la clase cn
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public VehiculoDAO() throws ClassNotFoundException {
        CN = new cn();  // Inicializa la conexión
    }

    public List<Vehiculo> listar() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try {
            con = CN.getConnection();  // Obtener la conexión
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getInt("idVehiculo"),
                        rs.getString("marcaVehiculo"),
                        rs.getString("modeloVehiculo"),
                        rs.getInt("anio")
                );
                lista.add(vehiculo);
            }
        } catch (Exception e) {
            System.err.println("Error al listar vehículos: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return lista;
    }

    public Vehiculo listarByID(int id) {
        Vehiculo vehiculo = null;
        String sql = "SELECT * FROM vehiculos WHERE idVehiculo = ?";

        try {
            con = CN.getConnection();  // Obtener la conexión
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                vehiculo = new Vehiculo(
                        rs.getInt("idVehiculo"),
                        rs.getString("marcaVehiculo"),
                        rs.getString("modeloVehiculo"),
                        rs.getInt("anio")
                );
            }
        } catch (Exception e) {
            System.err.println("Error al buscar vehículo por ID: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return vehiculo;
    }

    public boolean agregar(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos(marcaVehiculo, modeloVehiculo, anio) VALUES (?, ?, ?)";

        try {
            con = CN.getConnection();  // Obtener la conexión
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getMarcaVehiculo());
            ps.setString(2, vehiculo.getModeloVehiculo());
            ps.setInt(3, vehiculo.getAnio());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            System.err.println("Error al agregar vehículo: " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public boolean actualizar(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET marcaVehiculo = ?, modeloVehiculo = ?, anio = ? WHERE idVehiculo = ?";

        try {
            con = CN.getConnection();  // Obtener la conexión
            ps = con.prepareStatement(sql);
            ps.setString(1, vehiculo.getMarcaVehiculo());
            ps.setString(2, vehiculo.getModeloVehiculo());
            ps.setInt(3, vehiculo.getAnio());
            ps.setInt(4, vehiculo.getIdVehiculo());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            System.err.println("Error al actualizar vehículo: " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
 

    public boolean eliminarVehiculo(int idVehiculo) {
        String sql = "DELETE FROM vehiculos WHERE idVehiculo = ?";
        try {
            con = CN.getConnection(); // Asegúrate de que esto esté implementado correctamente
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVehiculo);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Retorna true si al menos una fila fue eliminada
        } catch (Exception e) {
            System.err.println("Error al eliminar vehículo: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }



}


