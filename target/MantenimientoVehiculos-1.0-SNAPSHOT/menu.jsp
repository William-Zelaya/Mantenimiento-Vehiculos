<!-- Navbar -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
  .navbar-custom {
    background-color: #343a40; /* Color oscuro */
  }
  .navbar-custom .navbar-brand,
  .navbar-custom .nav-link {
    color: #ffffff; /* Color blanco para contraste */
  }
  .navbar-custom .nav-link:hover {
    color: #ffc107; /* Color dorado al pasar el mouse */
  }
  .navbar-custom .navbar-toggler {
    border-color: #ffc107; /* Color del borde del botón en modo colapsado */
  }
  .navbar-custom .navbar-toggler-icon {
    background-color: #ffc107; /* Color del icono del botón */
  }
</style>

<nav class="navbar navbar-expand-lg navbar-custom">
  <div class="container-fluid">
    <a class="navbar-brand" href="">Examen2</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <!-- Main Menu Item 2 -->
        <li class="nav-item">
          <a class="nav-link" href="/MantenimientoVehiculos/vehiculo">Vehículos</a>
        </li>
        <!-- Main Menu Item 1 -->
        <li class="nav-item">
          <a class="nav-link" href="/MantenimientoVehiculos/mantenimiento">Mantenimientos</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
