-- Crear la base de datos
DROP DATABASE IF EXISTS StardamValley;
CREATE DATABASE IF NOT EXISTS StardamValley;
USE StardamValley;

-- Tabla Alimentos
CREATE TABLE Alimentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    cantidad_disponible INT DEFAULT 0
);

-- Tabla Productos
CREATE TABLE Productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    cantidad_disponible INT DEFAULT 0
);

-- Tabla Animales
CREATE TABLE Animales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('GALLINA', 'OVEJA', 'VACA', 'CERDO') NOT NULL,
    nombre VARCHAR(50),
    dia_insercion INT DEFAULT 0,
    peso DECIMAL(5, 2), -- solo para vacas
    id_alimento INT, -- referencia al alimento consumido
    id_producto INT, -- referencia al producto generado
    FOREIGN KEY (id_alimento) REFERENCES Alimentos(id),
    FOREIGN KEY (id_producto) REFERENCES Productos(id)
);

-- Tabla Produccion
CREATE TABLE HistorialProduccion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_animal INT NOT NULL,
    cantidad INT NOT NULL,
    fecha_produccion TIMESTAMP NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES Animales(id)
);

-- Tabla Consumo
CREATE TABLE HistorialConsumo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_animal INT NOT NULL,
    cantidad_consumida INT NOT NULL,
    fecha_consumo TIMESTAMP NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES Animales(id)
);

-- Tabla Transacciones
CREATE TABLE Transacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_transaccion ENUM('COMPRA', 'VENTA') NOT NULL,
    tipo_elemento ENUM('PRODUCTO', 'ALIMENTO', 'HUERTO') NOT NULL,
    precio DOUBLE NOT NULL,
    fecha_transaccion DATE NOT NULL
);

-- Insertar productos en la tabla Productos
INSERT INTO Productos (nombre, precio) VALUES
('Leche', 1.50),
('Huevos', 0.50),
('Lana', 25.60),
('Trufa', 150.20);

-- Insertar alimentos en la tabla Alimentos
INSERT INTO Alimentos (nombre, precio, cantidad_disponible) VALUES
('Maiz', 0.50, 5),
('Avena', 0.70, 5),
('Heno', 1.00, 5);

-- Insertar animales en la tabla Animales
INSERT INTO Animales (tipo, nombre, peso, id_alimento, id_producto) VALUES
('gallina', 'Clara', NULL, 1, 2),
('gallina', 'Pepa', NULL, 1, 2),
('gallina', 'Lola', NULL, 1, 2),
('oveja', 'Meri', NULL, 2, 3),
('oveja', 'Tina', NULL, 2, 3),
('oveja', 'Susi', NULL, 2, 3),
('vaca', 'Bovina', 600.00, 3, 1),
('vaca', 'Lola', 650.00, 3, 1),
('vaca', 'Nube', 580.00, 3, 1),
('cerdo', 'Gordito', NULL, 1, 4),
('cerdo', 'Rosa', NULL, 1, 4),
('cerdo', 'Tocino', NULL, 1, 4);

