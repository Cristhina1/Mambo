CREATE TABLE IF NOT EXISTS rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    img_categoria TEXT not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100),
    tipo_documento VARCHAR(20) NOT NULL,
    rol_id BIGINT NOT NULL,
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    contra VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_usuarios_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100),
    tipo_documento VARCHAR(100),
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    contra VARCHAR(100) NOT NULL,
    rol_id BIGINT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_cliente_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS destinatario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    email VARCHAR(20) NOT NULL,
    telefono VARCHAR(100) NOT NULL,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_destinatario_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS envio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_envio VARCHAR(50) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    ciudad VARCHAR(20) NOT NULL,
    referencia VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria_id BIGINT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    descripcion TEXT NOT NULL,
    imagen_url TEXT,
    CONSTRAINT fk_productos_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS pagos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    precio DECIMAL(10,2),
    tipo_pago VARCHAR(20),
    fecha_pago TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS compra (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    pago_id BIGINT NOT NULL,
    envio_id BIGINT,
    destinatario_id BIGINT,
    tipo_comprobante VARCHAR(50),
    estado_entrega VARCHAR(50),
    total DECIMAL(10,2),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_compra_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_compra_pago FOREIGN KEY (pago_id) REFERENCES pagos(id),
    CONSTRAINT fk_compra_envio FOREIGN KEY (envio_id) REFERENCES envio(id),
    CONSTRAINT fk_compra_destinatario FOREIGN KEY (destinatario_id) REFERENCES destinatario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS detalle_compra (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    compra_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2),
    sub_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_detalle_compra_compra FOREIGN KEY (compra_id) REFERENCES compra(id),
    CONSTRAINT fk_detalle_compra_producto FOREIGN KEY (producto_id) REFERENCES productos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;