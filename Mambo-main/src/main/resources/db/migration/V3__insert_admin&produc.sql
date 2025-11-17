INSERT INTO usuarios(
    nombreCompleto,
    tipo_documento_id,
    rol_id,
    numeroDocumento,
    email,
    telefono,
    contra
    
) VALUES(
    'Administrador Mambo',
    (SELECT id FROM tipo_documento WHERE nombre = 'DNI'),
    (SELECT id FROM rol WHERE nombre = 'ADMIN'),
    '12345678',
    'administrador@mambo.com',
    '912345678',
    '$2a$10$/ISBCyXLnsOzu1VspFKTz.Jq8gn7LlZ45mnNL3jvMMI3jg4/P3Gh6' -- contraseña: admin123
)