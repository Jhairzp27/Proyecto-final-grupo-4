-- database: ../database/DataBasePoliBank.sqlite
INSERT INTO Sexo (SexoNombre) 
VALUES  ('Masculino'),
        ('Femenino'),
        ('Hibrido');

INSERT INTO Rol (IdRol_Padre, RolNombre)
VALUES
    (NULL, 'Administrador App'),
    (1, 'Usuario');

INSERT INTO Persona (IdSexo, IdRol, PersonaNombre, PersonaApellido, PersonaCedula, PersonaFechaNacimiento) 
VALUES (1, 1, 'Sebastian', 'Sarasti', '1722217799', '2004-07-14');



INSERT INTO Cuenta (IdPersona, CuentaSaldo) 
VALUES (1, 500.75);
INSERT INTO AccesoCuenta (IdCuenta, AccesoCuentaClave)
VALUES  (1, 'profe1234');

-- INSERT INTO AccesoCuenta (AccesoCuentaUsuario
--                           ,IdCuenta
--                           ,AccesoCuentaClave          
--                           ,FechaModifica)
-- VALUES('administrador', (SELECT IdCuenta FROM Cuenta WHERE IdCuenta=IdAccesoCuenta),'profe1234',NULL);


INSERT INTO Historial (IdCuenta, HistorialMovimiento) 
VALUES (1, 'Depósito de $100');
