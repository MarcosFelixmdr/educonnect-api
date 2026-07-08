ALTER TABLE usuarios MODIFY COLUMN senha VARCHAR(255) NULL;
ALTER TABLE usuarios MODIFY COLUMN tipo ENUM('EDUCADOR', 'APRENDIZ', 'ADMIN') NOT NULL;
UPDATE usuarios SET tipo = 'APRENDIZ' WHERE tipo IS NULL OR tipo = '';
UPDATE usuarios SET 
  tipo = 'ADMIN', 
  senha = 'admteste123',
  nome = 'Administrador',
  bio = 'Perfil administrador do sistema EduConnect.',
  area_atuacao = 'Administração Geral'
WHERE email = 'adm';
INSERT IGNORE INTO usuarios (nome, email, tipo, senha, bio, area_atuacao, created_at)
VALUES ('Administrador', 'adm', 'ADMIN', 'admteste123', 'Perfil administrador do sistema EduConnect.', 'Administração Geral', NOW());
