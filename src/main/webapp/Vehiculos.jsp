<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Vehículos</title>

    <!-- Estilos de Bootstrap y DataTables -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f0f8ff;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            margin-top: 40px;
        }
        .btn-group-actions {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
    </style>
</head>
<body>
            <%@ include file="menu.jsp" %>

    <div class="container mt-5">
        <div class="bg-light p-3 mb-4">
            <h5>Información requerida: <small>Para registrar un nuevo vehículo</small></h5>
        </div>

        <!-- Formulario para agregar un nuevo vehículo -->
        <form action="/MantenimientoVehiculos/vehiculo" method="POST" class="mt-3">
            <input type="hidden" name="accion" value="agregar">
            <div class="mb-3">
                <label for="marca" class="form-label">Marca del Vehículo</label>
                <input type="text" class="form-control" id="marca" name="marcaVehiculo" placeholder="Ingrese la marca del vehículo" required>
            </div>
            <div class="mb-3">
                <label for="modelo" class="form-label">Modelo del Vehículo</label>
                <input type="text" class="form-control" id="modelo" name="modeloVehiculo" placeholder="Ingrese el modelo del vehículo" required>
            </div>
            <div class="mb-3">
                <label for="anio" class="form-label">Año</label>
                <input type="number" class="form-control" id="anio" name="anio" placeholder="Ingrese el año del vehículo" required>
            </div>
            <button type="submit" class="btn btn-primary"><i class="bi bi-save"></i> Agregar Vehículo</button>
        </form>

        <!-- Tabla con lista de vehículos -->
        <table id="vehiculosTable" class="table table-bordered mt-4">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Año</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vehiculo" items="${listaVehiculos}">
                    <tr>
                        <td>${vehiculo.idVehiculo}</td>
                        <td>${vehiculo.marcaVehiculo}</td>
                        <td>${vehiculo.modeloVehiculo}</td>
                        <td>${vehiculo.anio}</td>
                        <td class="btn-group-actions">
                            <!-- Botón de Mantenimiento -->
                            <button type="button" class="btn btn-sm btn-outline-warning" onclick="openMantenimientoModal('${vehiculo.idVehiculo}')">
                                <i class="bi bi-tools"></i> Mantenimiento
                            </button>

                            <!-- Botón de Editar -->
                            <button type="button" class="btn btn-sm btn-outline-primary" onclick="openEditModal('${vehiculo.idVehiculo}', '${vehiculo.marcaVehiculo}', '${vehiculo.modeloVehiculo}', '${vehiculo.anio}')">
                                <i class="bi bi-pencil"></i> Editar
                            </button>

                            <!-- Botón de Eliminar -->
                            <button type="button" class="btn btn-sm btn-outline-danger" onclick="deleteVehiculo('${vehiculo.idVehiculo}')">
                                <i class="bi bi-trash"></i> Eliminar
                            </button>


                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal para editar vehículo -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-light">
                    <h5 class="modal-title" id="editModalLabel">Modificar Información del Vehículo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form method="post" action="/MantenimientoVehiculos/vehiculo">
                        <input type="hidden" name="accion" value="editar">
                        <div class="mb-3">
                            <label for="editId" class="form-label">ID</label>
                            <input type="text" name="idVehiculo" class="form-control" id="editId" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editMarca" class="form-label">Marca del Vehículo</label>
                            <input type="text" name="marcaVehiculo" class="form-control" id="editMarca" required>
                        </div>
                        <div class="mb-3">
                            <label for="editModelo" class="form-label">Modelo del Vehículo</label>
                            <input type="text" name="modeloVehiculo" class="form-control" id="editModelo" required>
                        </div>
                        <div class="mb-3">
                            <label for="editAnio" class="form-label">Año</label>
                            <input type="number" name="anio" class="form-control" id="editAnio" required>
                        </div>
                        <button type="submit" class="btn btn-primary"><i class="bi bi-save"></i> Modificar Vehículo</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

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
                        <label for="mantenimientoFecha" class="form-label">Fecha de Mantenimiento</label>
                        <input type="date" name="fechaMantenimiento" class="form-control" id="mantenimientoFecha" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="mantenimientoCosto" class="form-label">Costo</label>
                        <input type="number" step="0.01" name="costo" class="form-control" id="mantenimientoCosto" required>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Agregar Mantenimiento</button>
                </form>
            </div>
        </div>
    </div>
</div>



    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#vehiculosTable').DataTable({
                "language": {
                    "url": "https://cdn.datatables.net/plug-ins/1.13.5/i18n/es_es.json"
                }
            });
        });

        function openEditModal(id, marca, modelo, anio) {
            document.getElementById('editId').value = id;
            document.getElementById('editMarca').value = marca;
            document.getElementById('editModelo').value = modelo;
            document.getElementById('editAnio').value = anio;
            var editModal = new bootstrap.Modal(document.getElementById('editModal'));
            editModal.show();
        }

        function openMantenimientoModal(vehiculoId) {
            document.getElementById('mantenimientoVehiculoId').value = vehiculoId;
            var mantenimientoModal = new bootstrap.Modal(document.getElementById('mantenimientoModal'));
            mantenimientoModal.show();
        }



        function deleteVehiculo(id) {
            if (confirm('¿Está seguro de que desea eliminar este vehículo?')) {
                const url = "/MantenimientoVehiculos/vehiculo/" + id;

                fetch(url, {
                    method: 'DELETE' // Cambiamos a DELETE en lugar de GET
                }).then(response => {
                    if (response.ok) {
                        window.location.reload(); // Recargamos la página si la eliminación fue exitosa
                    } else {
                        alert('Error al eliminar el vehículo.');
                    }
                }).catch(error => {
                    console.error('Error en la solicitud de eliminación:', error);
                    alert('Ocurrió un error al intentar eliminar el vehículo.');
                });
            }
        }

    </script>
</body>
</html>
