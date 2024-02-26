-- database: ../database/DataBasePoliBank.sqlite
DROP TABLE IF EXISTS Transferencia;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Sexo;

CREATE TABLE Sexo (
    IdSexo                  INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre                  TEXT NOT NULL UNIQUE,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A',
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE
);

CREATE TABLE Usuario (
    IdUsuario               INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre                  TEXT NOT NULL UNIQUE,
    Cedula                  VARCHAR(10) NOT NULL UNIQUE,
    Username                TEXT NOT NULL UNIQUE,
    Clave                   TEXT NOT NULL,
    Email                   TEXT NOT NULL UNIQUE,
    IdSexo                  INTEGER NOT NULL REFERENCES Sexo (IdSexo),
    Saldo                   REAL NOT NULL DEFAULT (0.0),
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A',
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE
);

CREATE TABLE Transferencia (
    IdTransferencia         INTEGER PRIMARY KEY AUTOINCREMENT,
    IdUsuarioEnvia          INTEGER NOT NULL REFERENCES Usuario (IdUsuario),
    IdUsuarioRecibe         INTEGER NOT NULL REFERENCES Usuario (IdUsuario),
    Monto                   REAL NOT NULL,
    Fecha                   DATE NOT NULL,
    Estado                  VARCHAR(1) NOT NULL DEFAULT 'A',
    FechaCrea               DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')),
    FechaModifica           DATE
);