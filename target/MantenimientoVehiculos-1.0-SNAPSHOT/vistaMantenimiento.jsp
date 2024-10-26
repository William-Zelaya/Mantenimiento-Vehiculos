<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Mantenimientos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
            <%@ include file="menu.jsp" %>

    <div class="container mt-5">
        <h1>Gestión de Mantenimientos</h1>

        <!-- Tabla con lista de mantenimientos -->
        <table class="table table-bordered mt-4">
            <thead class="table-dark">
                <tr>
                    <th>ID Mantenimiento</th>
                    <th>Fecha</th>
                    <th>Marca del Vehículo</th> <!-- Cambiar ID Vehículo por Marca -->
                    <th>Modelo del Vehículo</th> <!-- Añadir columna para Modelo -->
                    <th>Costo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mantenimiento" items="${listaMantenimientos}">
                    <tr>
                        <td>${mantenimiento.idMantenimiento}</td>
                        <td>${mantenimiento.fechaMantenimiento}</td>
                        <td>${mantenimiento.marcaVehiculo}</td> <!-- Mostrar Marca -->
                        <td>${mantenimiento.modeloVehiculo}</td> <!-- Mostrar Modelo -->
                        <td>${mantenimiento.costo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Modal para Mantenimiento -->
        <div class="modal fade" id="mantenimientoModal" tabindex="-1" aria-labelledby="mantenimientoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-light">
                        <h5 class="modal-title" id="mantenimientoModalLabel">Registrar Mantenimiento</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/MantenimientoVehiculos/mantenimiento">
                            <input type="hidden" name="accion" value="agregar">
                            <input type="hidden" name="vehiculoID" id="mantenimientoVehiculoId">
                            <div class="mb-3">
                                <label for="mantenimientoCosto" class="form-label">Costo</label>
                                <input type="number" step="0.01" name="costo" class="form-control" id="mantenimientoCosto" required>
                            </div>
                            <div class="mb-3">
                                <label for="fechaMantenimiento" class="form-label">Fecha de Mantenimiento</label>
                                <input type="date" name="fecha" class="form-control" id="fechaMantenimiento" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Agregar Mantenimiento</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>

     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        function openMantenimientoModal(vehiculoId) {
            document.getElementById('mantenimientoVehiculoId').value = vehiculoId;
            var mantenimientoModal = new bootstrap.Modal(document.getElementById('mantenimientoModal'));
            mantenimientoModal.show();
        }
    </script>
</body>
</html>

