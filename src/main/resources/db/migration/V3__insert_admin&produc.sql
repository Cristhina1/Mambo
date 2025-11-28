INSERT INTO usuarios (
    nombre_completo,
    tipo_documento,
    rol_id,
    numero_documento,
    email,
    telefono,
    contra
) VALUES
(
    'Administrador General',
    'DNI',
    1,
    '00000001',
    'admin@mambo.com',
    '987654321',
    '$2a$10$m2HDYoWlQf9osjBe.XEgM.NnOi31u4Qbo4d7g8XkNQKU.wf9I1jtK' /* admin*/
),
(
    'Vendedor Principal',
    'DNI',
    2,
    '00000002',
    'vendedor@mambo.com',
    '912345678',
    '$2a$10$dxb2fRyWeqA3POOdRlm4OOJpPTalHG/TIGnyLXTDjSp9nOXsbWZUS' /* vendedor*/
);
