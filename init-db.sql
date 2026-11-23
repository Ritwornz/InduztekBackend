-- ============================================================
-- SCRIPT DE INICIALIZACIÓN DE BASES DE DATOS - INDUZTEK BACKEND
-- Crea las 3 bases de datos independientes para los 3 microservicios
-- ============================================================

CREATE DATABASE IF NOT EXISTS db_usuarios CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_protocolos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_mediciones CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Otorgar privilegios completos al usuario de la aplicación
GRANT ALL PRIVILEGES ON db_usuarios.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON db_protocolos.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON db_mediciones.* TO 'root'@'%';

FLUSH PRIVILEGES;
