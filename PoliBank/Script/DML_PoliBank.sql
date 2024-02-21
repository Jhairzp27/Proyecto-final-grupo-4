-- database: ../database/DataBasePoliBank.sqlite
INSERT INTO Sexo (SexoNombre
                  ,FechaModifica) 
VALUES  ('Masculino', NULL),
        ('Femenino', NULL),
        ('Hibrido', NULL);

INSERT INTO Persona (IdSexo, PersonaNombre, PersonaApellido, PersonaCedula, PersonaFechaNacimiento, FechaModifica) 
VALUES (1, 'Sebastian', 'Sarasti', '1722217799', '2004-07-14', NULL);



INSERT INTO Cuenta (IdPersona, CuentaNumero, CuentaSaldo, FechaModifica) 
VALUES (1, 0000001, 500.75, NULL);
INSERT INTO AccesoCuenta (IdCuenta, AccesoCuentaUsuario, AccesoCuentaClave, FechaModifica)
VALUES ((SELECT IdCuenta FROM Cuenta WHERE CuentaNumero = 0000001), 'administrador', 'profe1234', NULL);

-- INSERT INTO AccesoCuenta (AccesoCuentaUsuario
--                           ,IdCuenta
--                           ,AccesoCuentaClave          
--                           ,FechaModifica)
-- VALUES('administrador', (SELECT IdCuenta FROM Cuenta WHERE IdCuenta=IdAccesoCuenta),'profe1234',NULL);


INSERT INTO Historial (IdCuenta, HistorialMovimiento, FechaModifica) 
VALUES (1, 'Depósito de $100', NULL);


