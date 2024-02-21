-- database: ../database/DataBasePoliBank.sqlite
DROP TABLE IF EXISTS Historial;
DROP TABLE IF EXISTS AccesoCuenta;
DROP TABLE IF EXISTS Cuenta;

DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Sexo;



CREATE TABLE Sexo (
    IdSexo                  INTEGER PRIMARY KEY AUTOINCREMENT,
    SexoNombre              TEXT UNIQUE NOT NULL,
    FechaCrea               DATE DEFAULT CURRENT_TIMESTAMP,
    FechaModifica           DATE,
    Estado                  TEXT DEFAULT 'A'
);


CREATE TABLE Persona (
    IdPersona               INTEGER PRIMARY KEY AUTOINCREMENT,
    IdSexo                  INTEGER REFERENCES Sexo(IdSexo),
    PersonaNombre           TEXT NOT NULL,
    PersonaApellido         TEXT NOT NULL,
    PersonaCedula           TEXT UNIQUE NOT NULL,
    PersonaFechaNacimiento  DATE NOT NULL,
    FechaCrea               DATE DEFAULT CURRENT_TIMESTAMP,
    FechaModifica           DATE,
    Estado                  TEXT DEFAULT 'A'
);

CREATE TABLE Cuenta (
    IdCuenta                INTEGER PRIMARY KEY AUTOINCREMENT,
    IdPersona               INTEGER REFERENCES Persona(IdPersona),
    CuentaNumero            INTEGER UNIQUE NOT NULL,
    CuentaSaldo             REAL NOT NULL,
    FechaCrea               DATE DEFAULT CURRENT_TIMESTAMP,
    FechaModifica           DATE,
    Estado                  TEXT DEFAULT 'A'
);

CREATE TABLE AccesoCuenta (
    IdAccesoCuenta          INTEGER PRIMARY KEY AUTOINCREMENT,
    IdCuenta                INTEGER  REFERENCES Cuenta(IdCuenta),
    AccesoCuentaUsuario     TEXT UNIQUE NOT NULL,
    AccesoCuentaClave       TEXT UNIQUE NOT NULL,
    FechaCrea               DATE DEFAULT CURRENT_TIMESTAMP,
    FechaModifica           DATE,
    Estado                  TEXT DEFAULT 'A'
);

CREATE TABLE Historial (
    IdHistorial             INTEGER PRIMARY KEY AUTOINCREMENT,
    IdCuenta                INTEGER REFERENCES Cuenta(IdCuenta),
    HistorialMovimiento     TEXT NOT NULL,
    FechaCrea               DATE DEFAULT CURRENT_TIMESTAMP,
    FechaModifica           DATE,
    Estado                  TEXT DEFAULT 'A'
);




