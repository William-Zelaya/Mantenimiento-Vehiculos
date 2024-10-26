package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import modelos.Mantenimiento;
import modelosDAO.MantenimientoDAO;

@WebServlet(name = "MantenimientoController", urlPatterns = {"/mantenimiento"})
public class MantenimientoController extends HttpServlet {

    private MantenimientoDAO mantenimientoDAO;

    @Override
    public void init() throws ServletException {
        try {
            mantenimientoDAO = new MantenimientoDAO();
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error al inicializar MantenimientoDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Mantenimiento> listaMantenimientos = mantenimientoDAO.listar();
        request.setAttribute("listaMantenimientos", listaMantenimientos);
        request.getRequestDispatcher("vistaMantenimiento.jsp").forward(request, response);
    }
    
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String accion = request.getParameter("accion");
    if ("agregar".equals(accion)) {
        // Obtener los datos del formulario
        String fecha = request.getParameter("fecha");
        int vehiculoID = Integer.parseInt(request.getParameter("vehiculoID"));
        double costo = Double.parseDouble(request.getParameter("costo"));

        // Crear objeto Mantenimiento
        Mantenimiento mantenimiento = new Mantenimiento(fecha, vehiculoID, costo);

        // Llamar al método agregar del DAO
        boolean agregado = mantenimientoDAO.agregar(mantenimiento);
        
        // Redirigir o mostrar un mensaje según el resultado
        if (agregado) {
            response.sendRedirect("mantenimiento"); // Redirige a la lista de mantenimientos
        } else {
            request.setAttribute("error", "Error al agregar el mantenimiento");
            request.getRequestDispatcher("vistaMantenimiento.jsp").forward(request, response);
        }
    }
}

}
