-- database: ../database/DataBasePoliBank.sqlite
DROP TABLE IF EXISTS Historial;
DROP TABLE IF EXISTS AccesoCuenta;
DROP TABLE IF EXISTS Cuenta;

DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Sexo;
DROP TABLE IF EXISTS Rol;

CREATE TABLE Rol (
    IdRol                   INTEGER PRIMARY KEY AUTOINCREMENT,
    IdRol_Padre             INTEGER REFERENCES Rol (IdRol),
    RolNombre               TEXT UNIQUE NOT NULL,
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A'
);

CREATE TABLE Sexo (
    IdSexo                  INTEGER PRIMARY KEY AUTOINCREMENT,
    SexoNombre              TEXT UNIQUE NOT NULL,
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A'
);


CREATE TABLE Persona (
    IdPersona               INTEGER PRIMARY KEY AUTOINCREMENT,
    IdSexo                  INTEGER REFERENCES Sexo(IdSexo),
    IdRol                   INTEGER REFERENCES Rol (IdRol),
    PersonaNombre           TEXT NOT NULL,
    PersonaApellido         TEXT NOT NULL,
    PersonaCedula           TEXT UNIQUE NOT NULL,
    PersonaFechaNacimiento  DATE NOT NULL,
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A'
);

CREATE TABLE Cuenta (
    IdCuenta                INTEGER PRIMARY KEY AUTOINCREMENT,
    IdPersona               INTEGER REFERENCES Persona(IdPersona),
    CuentaSaldo             REAL NOT NULL,
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A'
);

CREATE TABLE AccesoCuenta (
    IdAccesoCuenta          INTEGER PRIMARY KEY AUTOINCREMENT,
    IdCuenta                INTEGER  REFERENCES Cuenta(IdCuenta),
    AccesoCuentaClave       TEXT UNIQUE NOT NULL,
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A'
);

CREATE TABLE Historial (
    IdHistorial             INTEGER PRIMARY KEY AUTOINCREMENT,
    IdCuenta                INTEGER REFERENCES Cuenta(IdCuenta),
    HistorialMovimiento     TEXT NOT NULL,
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A'
);