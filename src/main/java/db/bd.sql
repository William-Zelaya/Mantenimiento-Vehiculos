CREATE DATABASE mantenimientovehiculos;

USE mantenimientovehiculos;

CREATE TABLE Vehiculos (
    IDVehiculo INT PRIMARY KEY AUTO_INCREMENT,
    MarcaVehiculo VARCHAR(50),
    ModeloVehiculo VARCHAR(50),
    Anio INT
);

CREATE TABLE Mantenimiento (
    IDMantenimiento INT PRIMARY KEY AUTO_INCREMENT,
    FechaMantenimiento DATE,
    VehiculoID INT,
    Costo DECIMAL(10, 2),
    FOREIGN KEY (VehiculoID) REFERENCES Vehiculos(IDVehiculo)
);

INSERT INTO Vehiculos (IDVehiculo, MarcaVehiculo, ModeloVehiculo, Anio)
VALUES
(1, 'Toyota', 'Corolla', 2020),
(2, 'Honda', 'Civic', 2019);
INSERT INTO Mantenimiento (IDMantenimiento, FechaMantenimiento, VehiculoID, Costo)
VALUES
(1, '2024-09-01', 1, 150.50),
(2, '2024-10-05', 2, 200.00);



SELECT * FROM vehiculos
SELECT * FROM Mantenimiento