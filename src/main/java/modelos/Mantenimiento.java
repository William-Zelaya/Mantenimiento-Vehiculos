package modelos;

public class Mantenimiento {
    private int idMantenimiento;
    private String fechaMantenimiento;
    private int vehiculoID;
    private double costo;
    private String marcaVehiculo; // Nueva propiedad
    private String modeloVehiculo; // Nueva propiedad

    public Mantenimiento() {}

    public Mantenimiento(String fechaMantenimiento, int vehiculoID, double costo) {
        this.fechaMantenimiento = fechaMantenimiento;
        this.vehiculoID = vehiculoID;
        this.costo = costo;
    }
    
    public Mantenimiento(int idMantenimineto, String fechaMantenimiento, int vehiculoID, double costo) {
        this.idMantenimiento = idMantenimineto;
        this.fechaMantenimiento = fechaMantenimiento;
        this.vehiculoID = vehiculoID;
        this.costo = costo;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(String fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public int getVehiculoID() {
        return vehiculoID;
    }

    public void setVehiculoID(int vehiculoID) {
        this.vehiculoID = vehiculoID;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    // Getters y Setters para marca y modelo
    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }
}
