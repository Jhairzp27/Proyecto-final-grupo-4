-- database: ../database/DataBasePoliBank.sqlite
INSERT OR IGNORE INTO Sexo (Nombre)
VALUES  ('Masculino'),
        ('Femenino');

INSERT  OR IGNORE INTO Usuario (Nombre, Cedula, Username, Clave, Email, IdSexo) 
VALUES 
    ('Christian Pisco', '3453464543', 'chris', 'chris1234', 'chris27@gmail.com', 1);
