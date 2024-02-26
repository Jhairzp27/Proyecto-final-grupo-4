-- database: ../database/DataBasePoliBank.sqlite
INSERT INTO Sexo (Nombre)
VALUES  ('Masculino'),
        ('Femenino');

INSERT INTO Usuario (Nombre, Cedula, Username, Clave, Email, IdSexo) 
VALUES 
    ('Christian Pisco', '3453464543', 'chris', 'chris1234', 'chris27@gmail.com', 1);
    
SELECT 
t.IdTransferencia IdTransferencia,                     
uenvia.Nombre UsuarioEnvia,                                   
urecibe.Nombre UsuarioRecibe,                                 
t.Monto Monto,                                                
t.Fecha Fecha                                                 
FROM Transferencia t                                          
JOIN Usuario uenvia ON t.IdUsuarioEnvia = uenvia.IdUsuario    
JOIN Usuario urecibe ON t.IdUsuarioRecibe = urecibe.IdUsuario 
WHERE t.Estado = 'A'
AND (t.IdUsuarioEnvia = 1 OR t.IdUsuarioRecibe = 1);

