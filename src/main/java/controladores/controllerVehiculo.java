package controladores;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Vehiculo;
import modelosDAO.VehiculoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllerVehiculo", urlPatterns = {"/vehiculo",})
public class controllerVehiculo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VehiculoDAO vehiculoDAO = new VehiculoDAO();  // Inicializa el DAO
            RequestDispatcher dispatcher;

            String accion = request.getParameter("accion");

            // Manejo de eliminación desde doGet
            if ("eliminar".equals(accion)) {
                try {
                    int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
                    boolean eliminado = vehiculoDAO.eliminarVehiculo(idVehiculo);
                    
                    if (eliminado) {
                        System.out.println("Vehículo eliminado con ID: " + idVehiculo);
                    } else {
                        System.out.println("No se pudo eliminar el vehículo con ID: " + idVehiculo);
                    }
                    
                    // Redireccionar a la lista de vehículos después de eliminar
                    response.sendRedirect(request.getContextPath() + "/vehiculo");
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("ID de vehículo no válido: " + e.getMessage());
                }
            }

            // Manejo de listar un vehículo por ID
            if (request.getParameter("idVehiculo") != null) {
                int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
                Vehiculo vehiculo = vehiculoDAO.listarByID(idVehiculo);
                if (vehiculo != null) {
                    request.setAttribute("vehiculo", vehiculo);
                    dispatcher = request.getRequestDispatcher("detalleVehiculo.jsp");
                } else {
                    request.setAttribute("error", "Vehículo no encontrado");
                    dispatcher = request.getRequestDispatcher("error.jsp");
                }
                dispatcher.forward(request, response);
                return;
            }

            // Listar todos los vehículos
            List<Vehiculo> listaVehiculos = vehiculoDAO.listar();
            request.setAttribute("listaVehiculos", listaVehiculos);
            dispatcher = request.getRequestDispatcher("Vehiculos.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.err.println("Error en doGet: " + e.getMessage());
            e.printStackTrace();
        }
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        String accion = request.getParameter("accion");
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        if ("agregar".equals(accion)) {
            // Como el ID es auto-incrementable, no lo pasamos aquí
            String marcaVehiculo = request.getParameter("marcaVehiculo");
            String modeloVehiculo = request.getParameter("modeloVehiculo");
            int anio = Integer.parseInt(request.getParameter("anio"));

            // Crear el vehículo sin ID, ya que será asignado automáticamente por la base de datos
            Vehiculo vehiculo = new Vehiculo(marcaVehiculo, modeloVehiculo, anio);
            vehiculoDAO.agregar(vehiculo);
            response.sendRedirect(request.getContextPath() + "/vehiculo");

        } else if ("editar".equals(accion)) {
            // Aquí sí necesitamos el ID para actualizar
            int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
            String marcaVehiculo = request.getParameter("marcaVehiculo");
            String modeloVehiculo = request.getParameter("modeloVehiculo");
            int anio = Integer.parseInt(request.getParameter("anio"));

            // Crear el vehículo con el ID para poder actualizar
            Vehiculo vehiculo = new Vehiculo(idVehiculo, marcaVehiculo, modeloVehiculo, anio);
            vehiculoDAO.actualizar(vehiculo);
            response.sendRedirect(request.getContextPath() + "/vehiculo");
        }

    } catch (NumberFormatException e) {
        System.err.println("Error en doPost (número inválido): " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error en doPost: " + e.getMessage());
        e.printStackTrace();
    }
}

@Override
public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String pathInfo = request.getPathInfo(); // Obtiene el path después de /vehiculo/
    if (pathInfo == null || pathInfo.equals("/")) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Si no hay ID, retorna un error 400
        return;
    }
    
    try {
        // Extrae el ID del vehículo del path
        int idVehiculo = Integer.parseInt(pathInfo.substring(1)); // Ejemplo: /vehiculo/5 extrae el 5
        
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        vehiculoDAO.eliminarVehiculo(idVehiculo); // Llama al método para eliminar el vehículo
        
        // Respuesta sin contenido al eliminar con éxito
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    } catch (Exception e) {
        // Si hay un error, imprime el mensaje de error en la consola del servidor
        System.err.println("Error al eliminar el vehículo: " + e.getMessage());
        // Envía un error 500 (Error interno del servidor)
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
}

