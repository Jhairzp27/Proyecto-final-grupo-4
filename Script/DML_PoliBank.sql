-- database: ../database/DataBasePoliBank.sqlite
INSERT INTO Sexo (Nombre) 
VALUES  ('Masculino'),
        ('Femenino');

INSERT INTO Usuario (Nombre, Cedula, IdSexo, Clave) 
VALUES 
    ('Ángel Pastaz',      '1722217799', 1, 'userAngel'),
    ('Christian Pisco',   '3453464543', 1, 'userChris'),
    ('Isaac Proaño',      '3458209333', 1, 'userIsaac'),
    ('Jhair Zambrano',    '4515316516', 1, 'userJhair'),
    ('Jhordy Parra',      '0840120321', 1, 'userJhordy'),
    ('Ruth Rueda',        '8403169822', 2, 'userRuth'),
    ('Sebastián Ramos',   '4547898454', 1, 'userSebastianR'),
    ('Sebastián Sarasti', '3216886176', 1, 'userSebastianS');
