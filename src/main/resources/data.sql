-- Corrige a coluna senha para permitir NULL (caso tenha sido criada como NOT NULL)
ALTER TABLE usuarios MODIFY COLUMN senha VARCHAR(255) NULL;

-- Adiciona ADMIN ao ENUM tipo (necessario porque ddl-auto=update nao altera valores de ENUM)
ALTER TABLE usuarios MODIFY COLUMN tipo ENUM('EDUCADOR', 'APRENDIZ', 'ADMIN') NOT NULL;

-- Corrige registros com tipo vazio ou nulo para APRENDIZ
UPDATE usuarios SET tipo = 'APRENDIZ' WHERE tipo IS NULL OR tipo = '';

-- Garante que o admin tenha o tipo correto e dados sem problemas de acentuação
UPDATE usuarios SET 
  tipo = 'ADMIN', 
  senha = 'admteste123',
  nome = 'Administrador',
  bio = 'Perfil administrador do sistema EduConnect.',
  area_atuacao = 'Administração Geral'
WHERE email = 'adm';

-- Insere o administrador padrao se ainda nao existir
INSERT IGNORE INTO usuarios (nome, email, tipo, senha, bio, area_atuacao, created_at)
VALUES ('Administrador', 'adm', 'ADMIN', 'admteste123', 'Perfil administrador do sistema EduConnect.', 'Administração Geral', NOW());


