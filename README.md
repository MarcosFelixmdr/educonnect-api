# 🖥️ EduConnect API — Documentação do Back-end

Esta é a documentação completa dos endpoints REST disponibilizados pela API backend do **EduConnect**, desenvolvida em Java com Spring Boot. A API roda por padrão na porta `8080`.

---

## 🔐 1. Endpoints de Usuários (`/api/usuarios`)

### 1.1. Cadastrar Usuário
*   **Método:** `POST`
*   **Rota:** `/api/usuarios`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "nome": "Maria Silva",
      "email": "maria@educonnect.com",
      "senha": "senha123",
      "tipo": "EDUCADOR",
      "bio": "Professora há 10 anos.",
      "areaAtuacao": "Alfabetização de Adultos"
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 2,
      "nome": "Maria Silva",
      "email": "maria@educonnect.com",
      "tipo": "EDUCADOR",
      "bio": "Professora há 10 anos.",
      "areaAtuacao": "Alfabetização de Adultos",
      "foto": null,
      "createdAt": "2026-07-07T20:50:00"
    }
    ```

### 1.2. Login
*   **Método:** `POST`
*   **Rota:** `/api/usuarios/login`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "email": "maria@educonnect.com",
      "senha": "senha123"
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 2,
      "nome": "Maria Silva",
      "email": "maria@educonnect.com",
      "tipo": "EDUCADOR",
      "bio": "Professora há 10 anos.",
      "areaAtuacao": "Alfabetização de Adultos",
      "foto": null
    }
    ```

### 1.3. Buscar Usuário por ID
*   **Método:** `GET`
*   **Rota:** `/api/usuarios/{id}`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 2,
      "nome": "Maria Silva",
      "email": "maria@educonnect.com",
      "tipo": "EDUCADOR",
      "bio": "Professora há 10 anos.",
      "areaAtuacao": "Alfabetização de Adultos",
      "foto": null
    }
    ```

### 1.4. Atualizar Perfil (inclui Upload de Foto)
*   **Método:** `PUT`
*   **Rota:** `/api/usuarios/{id}`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "nome": "Maria Silva Editada",
      "email": "maria@educonnect.com",
      "tipo": "EDUCADOR",
      "bio": "Nova biografia atualizada.",
      "areaAtuacao": "Alfabetização Avançada",
      "foto": "data:image/png;base64,iVBORw0KGgoAAAANS..."
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 2,
      "nome": "Maria Silva Editada",
      "email": "maria@educonnect.com",
      "tipo": "EDUCADOR",
      "bio": "Nova biografia atualizada.",
      "areaAtuacao": "Alfabetização Avançada",
      "foto": "data:image/png;base64,iVBORw0KGgoAAAANS..."
    }
    ```

---

## 📚 2. Endpoints de Cursos (`/api/cursos`)

### 2.1. Listar Cursos
*   **Método:** `GET`
*   **Rota:** `/api/cursos`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    [
      {
        "id": 1,
        "titulo": "Alfabetização Básica 1",
        "descricao": "Curso introdutório voltado para a alfabetização inicial de adultos.",
        "categoria": "Alfabetização",
        "cargaHoraria": 40,
        "nivel": "INICIANTE",
        "educadorId": 2,
        "educadorNome": "Maria Silva",
        "status": "ATIVO"
      }
    ]
    ```

### 2.2. Criar Curso
*   **Método:** `POST`
*   **Rota:** `/api/cursos`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "titulo": "Matemática Financeira Básica",
      "descricao": "Noções essenciais de finanças para o cotidiano.",
      "categoria": "Matemática",
      "cargaHoraria": 20,
      "nivel": "INICIANTE",
      "educadorId": 2,
      "status": "PENDENTE"
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 2,
      "titulo": "Matemática Financeira Básica",
      "descricao": "Noções essenciais de finanças para o cotidiano.",
      "categoria": "Matemática",
      "cargaHoraria": 20,
      "nivel": "INICIANTE",
      "educadorId": 2,
      "educadorNome": "Maria Silva",
      "status": "PENDENTE"
    }
    ```

### 2.3. Atualizar Status (Aprovação de Curso pelo Admin)
*   **Método:** `PUT`
*   **Rota:** `/api/cursos/{id}`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "titulo": "Matemática Financeira Básica",
      "descricao": "Noções essenciais de finanças para o cotidiano.",
      "categoria": "Matemática",
      "cargaHoraria": 20,
      "nivel": "INICIANTE",
      "educadorId": 2,
      "status": "ATIVO"
    }
    ```
*   **Resposta Esperada (JSON - 200 OK se a prova de 10 questões estiver cadastrada):**
    ```json
    {
      "id": 2,
      "titulo": "Matemática Financeira Básica",
      "status": "ATIVO",
      "educadorId": 2
    }
    ```
*   **Resposta Esperada (Erro - 400 Bad Request se não houver prova de 10 questões cadastrada):**
    ```json
    {
      "status": 400,
      "message": "Não é possível ativar o curso: A avaliação precisa ter exatamente 10 questões cadastradas (atualmente possui: 0/10).",
      "timestamp": "2026-07-07T20:53:00"
    }
    ```

---

## 📝 3. Endpoints de Matrículas (`/api/matriculas`)

### 3.1. Solicitar Matrícula
*   **Método:** `POST`
*   **Rota:** `/api/matriculas`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "aprendizId": 3,
      "cursoId": 1,
      "status": "PENDENTE"
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 5,
      "aprendizId": 3,
      "aprendizNome": "João Souza",
      "cursoId": 1,
      "cursoTitulo": "Alfabetização Básica 1",
      "dataInscricao": "2026-07-07",
      "status": "PENDENTE"
    }
    ```

### 3.2. Aprovar/Atualizar Matrícula
*   **Método:** `PUT`
*   **Rota:** `/api/matriculas/{id}`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "id": 5,
      "aprendizId": 3,
      "cursoId": 1,
      "status": "ATIVA"
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 5,
      "aprendizId": 3,
      "cursoId": 1,
      "status": "ATIVA"
    }
    ```

---

## 📄 4. Endpoints de Conteúdos (`/api/conteudos`)

### 4.1. Listar Conteúdos de um Curso
*   **Método:** `GET`
*   **Rota:** `/api/conteudos/curso/{cursoId}`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    [
      {
        "id": 10,
        "titulo": "Aula 1: Vogais e Consoantes",
        "descricao": "Explicação básica sobre o alfabeto.",
        "videoUrl": "https://www.youtube.com/watch?v=12345",
        "ordem": 1,
        "cursoId": 1
      }
    ]
    ```

### 4.2. Criar Conteúdo
*   **Método:** `POST`
*   **Rota:** `/api/conteudos`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "titulo": "Aula 2: Formando Sílabas",
      "descricao": "Aprenda a juntar consoantes e vogais.",
      "videoUrl": "https://www.youtube.com/watch?v=67890",
      "ordem": 2,
      "cursoId": 1
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 11,
      "titulo": "Aula 2: Formando Sílabas",
      "descricao": "Aprenda a juntar consoantes e vogais.",
      "videoUrl": "https://www.youtube.com/watch?v=67890",
      "ordem": 2,
      "cursoId": 1
    }
    ```

---

## 📈 5. Endpoints de Progresso (`/api/progresso`)

### 5.1. Listar Progresso do Aluno
*   **Método:** `GET`
*   **Rota:** `/api/progresso/{aprendizId}`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    [
      {
        "id": 1,
        "aprendizId": 3,
        "conteudoId": 10,
        "concluido": true
      }
    ]
    ```

### 5.2. Marcar/Desmarcar Conclusão de Aula
*   **Método:** `POST`
*   **Rota:** `/api/progresso/aprendiz/{aprendizId}/conteudo/{conteudoId}?concluido=true`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 1,
      "aprendizId": 3,
      "conteudoId": 10,
      "concluido": true
    }
    ```

---

## ⭐ 6. Endpoints de Avaliações/Feedback (`/api/avaliacoes`)

### 6.1. Deixar Avaliação / Comentário
*   **Método:** `POST`
*   **Rota:** `/api/avaliacoes`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "cursoId": 1,
      "aprendizId": 3,
      "aprendizNome": "João Souza",
      "nota": 5,
      "comentario": "Excelente curso! Consegui aprender as sílabas perfeitamente."
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 8,
      "cursoId": 1,
      "aprendizId": 3,
      "aprendizNome": "João Souza",
      "nota": 5,
      "comentario": "Excelente curso! Consegui aprender as sílabas perfeitamente.",
      "createdAt": "2026-07-07T21:00:00"
    }
    ```

---

## 📝 7. Endpoints do Sistema de Prova/Quiz (`/api/provas` e `/api/questoes`)

### 7.1. Buscar Prova do Curso
*   **Método:** `GET`
*   **Rota:** `/api/provas/curso/{cursoId}`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 4,
      "cursoId": 1,
      "titulo": "Avaliação Final de Alfabetização 1",
      "createdAt": "2026-07-07T21:10:00"
    }
    ```

### 7.2. Criar Prova para o Curso
*   **Método:** `POST`
*   **Rota:** `/api/provas`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "cursoId": 1,
      "titulo": "Avaliação Final de Alfabetização 1"
    }
    ```

### 7.3. Listar Questões da Prova
*   **Método:** `GET`
*   **Rota:** `/api/questoes/prova/{provaId}`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    [
      {
        "id": 15,
        "provaId": 4,
        "enunciado": "Qual das alternativas abaixo contém apenas vogais?",
        "opcaoA": "A, E, I, O, U",
        "opcaoB": "B, C, D, F, G",
        "opcaoC": "A, B, C, D, E",
        "opcaoD": "X, Y, Z, W, K",
        "respostaCorreta": "A",
        "ordem": 1
      }
    ]
    ```

### 7.4. Adicionar Questão à Prova
*   **Método:** `POST`
*   **Rota:** `/api/questoes`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "provaId": 4,
      "enunciado": "Escolha a palavra que começa com a letra B:",
      "opcaoA": "Amigo",
      "opcaoB": "Bola",
      "opcaoC": "Casa",
      "opcaoD": "Dado",
      "respostaCorreta": "B"
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 16,
      "provaId": 4,
      "enunciado": "Escolha a palavra que começa com a letra B:",
      "opcaoA": "Amigo",
      "opcaoB": "Bola",
      "opcaoC": "Casa",
      "opcaoD": "Dado",
      "respostaCorreta": "B",
      "ordem": 2
    }
    ```

### 7.5. Submeter Avaliação Resolvida (Gabarito)
*   **Método:** `POST`
*   **Rota:** `/api/resultados/submeter`
*   **Corpo da Requisição (JSON):**
    ```json
    {
      "provaId": 4,
      "aprendizId": 3,
      "respostas": {
        "15": "A",
        "16": "B",
        "17": "C",
        "18": "A",
        "19": "D",
        "20": "B",
        "21": "C",
        "22": "A",
        "23": "D",
        "24": "A"
      }
    }
    ```
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 30,
      "provaId": 4,
      "aprendizId": 3,
      "acertos": 8,
      "aprovado": true,
      "createdAt": "2026-07-07T21:15:00"
    }
    ```

### 7.6. Buscar Resultado Obtido
*   **Método:** `GET`
*   **Rota:** `/api/resultados/prova/{provaId}/aprendiz/{aprendizId}`
*   **Resposta Esperada (JSON - 200 OK):**
    ```json
    {
      "id": 30,
      "provaId": 4,
      "aprendizId": 3,
      "acertos": 8,
      "aprovado": true,
      "createdAt": "2026-07-07T21:15:00"
    }
    ```
